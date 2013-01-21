(ns sicpclojure.core
  (:require [clojure.string :as string])
  (:require [hickory.core :refer [as-hiccup parse]])
  (:require [clojure.java.io :as io])
  (:require [fs.core :as fs])
  (:import [org.pegdown PegDownProcessor Extensions]))

(def config {:path-to-code "resources/code/"
             :path-to-text "resources/text/"
             :deploy-directory "deploy/"
             :ignore #{".scss"}})

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
