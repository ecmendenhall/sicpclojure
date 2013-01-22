(ns sicpclojure.core
  (:require [sicpclojure.templates.page :as page-template])
  (:require [sicpclojure.templates.cover :as cover-template])
  (:require [clojure.string :as string])
  (:require [hickory.core :refer [as-hiccup parse]])
  (:require [hickory.zip :refer [hiccup-zip]])
  (:require [clojure.zip :as zip])
  (:require [clojure.java.io :as io])
  (:require [fs.core :as fs])
  (:import [org.pegdown PegDownProcessor Extensions]))

(def config {:path-to-code "resources/code/"
             :path-to-text "resources/text/"
             :deploy-directory "deploy/"
             :ignore #{".scss"}
             :complete [10 11 12]})

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
           (slurp (str (config :path-to-code) 
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

  (->> page 
       (slurp)
       (insert-code-blocks)
       (markdown-to-html)
       (parse)
       (as-hiccup)))

(defn deploy
  ""
  []
  (defn get-or-mkdir [dir]
    (when-not (fs/exists? dir)
      (fs/mkdir dir))
    dir)

  ;; If deploy directory doesn't exist, create it.
  ;; Copy resources/static to deploy
  (fs/copy-dir "resources/static" 
               (get-or-mkdir (config :deploy-directory)))
  
  ;; Remove files in config :ignore
  (defn clean-up [dir]
    (defn remove-ignored [file]    
      (when (contains? (config :ignore) (fs/extension file))
        (fs/delete file)))
    (defn get-files [root _ files]
      (map (fn [file] (str (.getPath root) "/" file)) files))
    (map remove-ignored (flatten (fs/walk get-files dir))))

  (clean-up (str (config :deploy-directory) "static"))
  
  (spit (str (config :deploy-directory)
             "index.html")
        (cover-template/render))

  (defn render-and-spit [page]
    (let [page-dir (get-or-mkdir (str (config :deploy-directory)
                                      "pages"))]
      (spit (str page-dir "/" page ".html")
            (page-template/render (get-page (str (config :path-to-text)
                                                 page
                                                 ".md"))))))

  (map render-and-spit (config :complete)))

(defn get-headers [page]
  (map (fn [el] [(first el) 
                 (string/join (filter string?
                                      (flatten (rest el))))])
       (filter (fn [el] (contains? #{:h1 :h2 :h3 :h4} (first el))) 
               (-> (hiccup-zip (get-page page))
                   zip/next
                   zip/next
                   zip/next 
                   zip/children))))

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
          zipper
        :else
          (recur (zip/next zipper)))))
  (let [page-zipper (hiccup-zip (get-page page))]
    (zip-through page-zipper)))
