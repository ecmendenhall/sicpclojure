(ns sicpclojure.core
  (:require [sicpclojure.config :as config])
  (:require [sicpclojure.templates.contents :as contents-template])
  (:require [sicpclojure.templates.page :as page-template])
  (:require [sicpclojure.templates.cover :as cover-template])
  (:require [clojure.zip :as zip])
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as string])
  (:require [hickory.core :refer [as-hiccup parse]])
  (:require [hickory.zip :refer [hiccup-zip]])
  (:require [fs.core :as fs])
  (:require [watchtower.core :as wt])
  (:import [org.pegdown PegDownProcessor Extensions]))

(defn page-path [page]
  (str (config/build :path-to-text)
       page
       ".md"))

(defn get-page 
  ""
  [page]

  (defn insert-code-blocks 
    ""
    [markdown]

    (defn get-code 
      ""
      [template-match]
      (str "```clojure\n"
           (slurp (str (config/build :path-to-code) 
                       (second template-match)))
           "```"))

    (let [code-block-regex #"\{\{\s*([a-zA-Z0-9]+(?:[\.|-][a-zA-Z0-9]+)*\.clj)\s*\}\}"]
      (string/replace markdown code-block-regex get-code)))

  (defn markdown-to-html 
    ""
    [markdown]
    (let [processor (PegDownProcessor. 
                    (reduce bit-or [(. Extensions FENCED_CODE_BLOCKS)]))]
      (.markdownToHtml processor markdown)))

  (defn add-header-ids [page]
    (defn zip-through [zipper]
      (let [el (zip/node zipper)]
        (cond 
          (contains? #{:h1 :h2 :h3} (first el))
            (let [section-id (second 
                               (string/split (:href (second (nth el 2))) 
                                             #"#"))]
              (recur (zip/next (zip/replace zipper (assoc-in el [1] {:id section-id})))))
          (zip/end? zipper)
            (list (first (zip/root zipper)))
          :else
            (recur (zip/next zipper)))))
    (let [page-zipper (hiccup-zip page)]
      (zip-through page-zipper)))

  (->> page 
       (slurp)
       (insert-code-blocks)
       (markdown-to-html)
       (parse)
       (as-hiccup)
       (add-header-ids)))

(defn deploy!
  ""
  []
  (defn get-or-mkdir [dir]
    (when-not (fs/exists? dir)
      (fs/mkdir dir))
    dir)

  ;; If deploy directory doesn't exist, create it.
  ;; Copy resources/static to deploy
  (fs/copy-dir "resources/static" 
               (get-or-mkdir (config/build :deploy-directory)))
  
  ;; Remove files in config :ignore
  (defn clean-up [dir]
    (defn remove-ignored [file]    
      (when (contains? (config/build :ignore) (fs/extension file))
        (fs/delete file)))
    (defn get-files [root _ files]
      (map (fn [file] (str (.getPath root) "/" file)) files))
    (map remove-ignored (flatten (fs/walk get-files dir))))

  (clean-up (str (config/build :deploy-directory) "static"))
  
  (defn make-page-contents [page]

      (defn get-headers [page]
        (map (fn [el] [(first el) 
                       (string/join (filter string?
                                            (flatten (rest el))))])
             (filter (fn [el] (contains? #{:h1 :h2 :h3} (first el))) 
                     (-> (hiccup-zip (get-page (page-path page)))
                         zip/next
                         zip/next
                         zip/next 
                         zip/children))))

      [:ul (map (fn [i] [:li 
                         {:class (string/join (take-last 2 (str (first i))))} 
                         (last i)])
                (get-headers page))])

  (defn make-contents [pages]
    (map make-page-contents pages))

  (spit (str (config/build :deploy-directory)
             "index.html")
        (cover-template/render))

  (spit (str (get-or-mkdir (str (config/build :deploy-directory)
                                "pages"))
             "/contents.html")
        (contents-template/render (make-contents (config/build :complete))))

  (defn render-and-spit [page]
    (let [page-dir (get-or-mkdir (str (config/build :deploy-directory)
                                      "pages"))]
      (spit (str page-dir "/" page ".html")
            (page-template/render (make-page-contents page)
                                  (get-page (page-path page))
                                  page))))

  (map render-and-spit (config/build :complete)))

(defn watch [directories]
  (wt/watcher directories
           (wt/rate 50)
           (wt/file-filter (wt/extensions :js :css :png))
           (wt/on-change (fn [f] (println "File changed: " f)
                                 (println "Deploying!")
                                 (deploy!)))))
