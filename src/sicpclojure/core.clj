(ns sicpclojure.core
  (:require [sicpclojure.config :as config])
  (:require [sicpclojure.templates.contents :as contents-template])
  (:require [sicpclojure.templates.page :as page-template])
  (:require [sicpclojure.templates.cover :as cover-template])
  (:require [sicpclojure.templates.about :as about-template])
  (:require [sicpclojure.templates.404 :as notfound-template])
  (:require [clojure.zip :as zip])
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as string])
  (:require [clojure.walk :as walk])
  (:require [hiccup.core :as hiccup])
  (:require [hickory.core :refer [as-hiccup parse]])
  (:require [hickory.zip :refer [hiccup-zip]])
  (:require [fs.core :as fs])
  (:require [watchtower.core :as wt])
  (:require [ring.adapter.jetty :refer [run-jetty]])
  (:require [ring.middleware.file :refer [wrap-file]])
  (:require [compojure.core :refer [defroutes]])
  (:require [compojure.route :as route])
  (:import [org.pegdown PegDownProcessor Extensions]))

(defn page-path 
  "Takes a page number or string. Returns the path to a chapter page in the directory
  specified in config/build."
  [page]
  (str (config/build :path-to-text)
       page
       ".md"))

(defn get-page 
  "Takes the path to a chapter page. Returns a Hiccup representation of its rendered
  markdown."
  [page]

  (defn insert-code-blocks 
    "Replaces template placeholders in the given markdown string with code blocks."
    [markdown]

    (defn slurp-code-sample
      "Slurps and returns the given file from the code directory in config/build."
      [filename]
      (slurp (str (config/build :path-to-code) 
                  filename)))

    (defn get-code 
      "Takes a regex match vector. Gets a code example from the directory specified 
      in config/build and returns it formatted as an HTML code block."
      [template-match]
      (let [filename (second template-match)]
      (hiccup/html 
        [:div {:id filename :class "code-div"}
         [:pre
          [:code {:class "clojure"}
                 (slurp-code-sample filename)]]
         [:div {:class "source-link"} 
          [:a {:href (str (config/build :repo-url) 
                          "blob/master/resources/code/" 
                          filename)
               :target "_blank"
               :class "view-source"}
              "Source"]]])))

    (let [code-block-regex 
          #"\{\{\s*([a-zA-Z0-9]+(?:[\.|-][a-zA-Z0-9]+)*\.clj)\s*\}\}"]
          ; Finds, e.g. {{ 1.2.3.clj }} and captures filename
      (string/replace markdown code-block-regex get-code)))

  (defn process-footnotes 
    "Takes a markdown string. Returns a string with footnote markers [^fn-n] and 
    [fn-n] replaced by corresponding HTML tags."
    [markdown]

    (defn generate-reference 
      "Takes a regex match for a markdown reference and returns a superscript link
      to the id #fn(n)."
      [match]
      (let [n (second match)]
        (hiccup/html [:sup
             [:a {:href (str "#fn" n)
                  :id (str "r" n)}
                  n]])))

    (defn generate-footnote
      "Takes a regex match for a markdown footnote and returns a return link to the
      id #r(n)."
      [match]
      (let [n (second match)]
        (hiccup/html [:a {:href (str "#r" n)
                          :id (str "fn" n)}
                      "&#8617;"])))

    (let [reference-regex #"\[\^fn-([0-9]+)\]" ; Matches [^fn-n]
          footnote-regex #"\[fn-([0-9]+)\]"]   ; Matches [fn-n]
      (string/replace (string/replace markdown 
                                      reference-regex 
                                      generate-reference)
                      footnote-regex
                      generate-footnote)))

  (defn markdown-to-html 
    "Converts a markdown string to HTML. The Java PegDownProcessor class takes a
    bitmask of configuration options."
    [markdown]
    (let [processor (PegDownProcessor. 
                    (reduce bit-or [(. Extensions FENCED_CODE_BLOCKS)]))]
      (.markdownToHtml processor markdown)))

  (defn add-header-ids
    "Takes a Hiccup template, creates a zipper, and adds in-page anchor ids to the
    header elements. Returns a Hiccup template."
    [page]
    (defn zip-through [zipper]
      (let [el (zip/node zipper)]
        (cond 
          (contains? #{:h1 :h2 :h3} (first el))
            (let [section-id (second 
                               (string/split (:href (second (nth el 2))) 
                                             #"#"))]
              (recur (zip/next (zip/replace zipper (assoc-in el 
                                                             [1] 
                                                             {:id section-id})))))
          (zip/end? zipper)
            (first (zip/root zipper))
          :else
            (recur (zip/next zipper)))))
    (let [page-zipper (hiccup-zip page)]
      (zip-through page-zipper)))

  (defn escape 
    "Recursively walks a hiccup template and HTML escapes strings."
    [hiccup-template]
    (walk/postwalk (fn [el] (if (string? el)
                              (hiccup/h el)
                              el))
                   hiccup-template))

  (defn return-body 
    "Takes a hiccup template and returns the body element."
    [hiccup-template]
    (drop 2 (nth hiccup-template 3)))

  (->> page 
       (slurp)
       (insert-code-blocks)
       (process-footnotes)
       (markdown-to-html)
       (parse)
       (as-hiccup)
       (add-header-ids)
       (escape)
       (return-body)))

(defn deploy!
  "Renders templates for static pages and chapter pages listed as complete in 
  config/build. Makes and renders table of contents. Copies static resources to
  deploy directory."
  []
  (defn get-or-mkdir 
    "Takes a directory path. If it doesn't exist, creates it."
    [dir]
    (when-not (fs/exists? dir)
      (fs/mkdir dir))
    dir)

  ;; Copy resources/static to deploy
  (fs/copy-dir "resources/static" 
               (get-or-mkdir (config/build :deploy-directory)))
  
  (defn clean-up 
    "Takes a directory. Recursively removes files specified in config/build :ignore
    (e.g. .scss)"
    [dir]

    (defn remove-ignored [file]    
      (when (contains? (config/build :ignore) (fs/extension file))
        (fs/delete file)))

    (defn get-files [root _ files]
      (map (fn [file] (str (.getPath root) "/" file)) files))

    (map remove-ignored (flatten (fs/walk get-files dir))))

  (clean-up (str (config/build :deploy-directory) "static"))
  
  (defn make-page-contents
    "Takes a page number. Gets the corresponding page and returns a Hiccup list of
    its header elements, in order."
    [page]

      (defn get-headers 
        "Takes a page number. Zips through its rendered Hiccup template and returns
        a list of header elements." 
        [page]
        (map (fn [el] [(first el) 
                       (string/join (filter string?
                                            (flatten (rest el))))])
             (filter (fn [el] (contains? #{:h1 :h2 :h3} (first el))) 
                     (-> (hiccup-zip (get-page (page-path page)))
                         zip/children))))

      (defn make-li [header]
        "Takes a string. Makes a Hiccup list out of the header title."
        (let [section-regex #"[0-9](?:\.[0-9])*" ; Matches, 1, 1.1, 1.1.2, &c.
              header-type (first header)
              header-text (last header)]
          [:li {:class (string/join (take-last 2 (str header-type)))} 
               [:a {:href (str page 
                               ".html#sec_" 
                               (re-find section-regex header-text))}
                   header-text]]))

      [:ul (map make-li
                (get-headers page))])

  (defn make-contents 
    "Takes a vector of page numbers. Returns a list of Hiccup vectors."
    [pages]
    (map make-page-contents pages))
  
  ;; Render and spit static pages
  (spit (str (config/build :deploy-directory)
             "index.html")
        (cover-template/render))

  (spit (str (config/build :deploy-directory)
             "404.html")
        (notfound-template/render))

  (spit (str (config/build :deploy-directory)
             "about.html")
        (about-template/render (get-page (page-path "about")))) 
  
  ;; Render and spit table of contents
  (spit (str (get-or-mkdir (str (config/build :deploy-directory)
                                "pages"))
             "/contents.html")
        (contents-template/render (make-contents (config/build :complete))))

  (defn render-and-spit 
    "Takes a page number. Renders the given template and spits it to the deploy
    directory"
    [page]
    (let [page-dir (get-or-mkdir (str (config/build :deploy-directory)
                                      "pages"))]
      (spit (str page-dir "/" page ".html")
            (page-template/render (make-page-contents page)
                                  (get-page (page-path page))
                                  page))))

  (map render-and-spit (config/build :complete)))

;; Helper functions for development

(defn watch
  "Watches the given directories for changes. Redeploys when changes are detected." 
  [directories]
  (wt/watcher directories
           (wt/rate 50)
           (wt/file-filter (wt/extensions :css :js :png :md :clj))
           (wt/on-change (fn [f] (println "File changed: " f)
                                 (println "Deploying!")
                                 (deploy!)))))

(defn serve
  "Starts a simple Jetty server on port 3000, serving files from the deploy directory."
  ([]
   (defroutes dev-handler
   (route/files "/" {:root "deploy"}))
   (run-jetty dev-handler {:port 3000 :join? false})))


(defn dev-server 
  "Deploys, then starts the dev server. Pass in a directory to watch."
  ([]
   (deploy!)
   (serve))
  ([dir]
   (deploy!)
   (serve)
   (watch dir)))
