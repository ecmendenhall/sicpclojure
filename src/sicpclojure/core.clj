(ns sicpclojure.core
  (:require [sicpclojure.templates.page :as page-template])
  (:require [sicpclojure.templates.cover :as cover-template])
  (:require [clojure.string :as string])
  (:require [hickory.core :refer [as-hiccup parse]])
  (:require [clojure.java.io :as io])
  (:require [fs.core :as fs])
  (:import [org.pegdown PegDownProcessor Extensions]))

(def config {:path-to-code "resources/code/"
             :path-to-text "resources/text/"
             :deploy-directory "deploy/"
             :ignore #{".scss"}
             :complete ["10.md" "11.md" "12.md"]})

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
  ;; If deploy directory doesn't exist, create it.
  (when-not (fs/exists? (config :deploy-directory))
    (fs/mkdir (config :deploy-directory)))

  ;; Copy resources/static to deploy
  (fs/copy-dir "resources/static" (config :deploy-directory))
  
  ;; Remove files in config :ignore
  (defn clean-up [dir]
    (defn remove-ignored [file]
      (when (contains? (config :ignore) (fs/extension file))
        (fs/delete file)))
    (defn get-files [root _ files]
      (map (fn [file] (str (.getPath root) "/" file)) files))
    (map remove-ignored (flatten (fs/walk get-files dir))))

  (clean-up (str (config :deploy-directory) "static"))
  
  (map (fn [page] (spit (str page ".html")
                        (page-template/render 
                          (get-page (str (config :path-to-text)
                                         page)))))
       (config :complete)))
