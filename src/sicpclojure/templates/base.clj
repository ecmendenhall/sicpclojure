(ns sicpclojure.templates.base
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5, include-js, include-css]])
  (:require [clojure.string :as string]))

(def config {:js    ["require.js"]
             :css   ["style.css"
                     "solarized_light.css"]
             :fonts [["Lora" 
                       :size [400 700]]
                     ["Ubuntu Mono"]]
             :font-url "http://fonts.googleapis.com/css?family="})

(defn include-font [font]
  (let [font-name (string/replace (first font) " " "+")
        font-sizes (second (rest font))]
    (include-css (str (config :font-url)
                              font-name
                              (when font-sizes
                                (str ":"
                                     (string/join "," font-sizes)))))))

(defn include-local [file include-fn]
  (let [filetype (second (re-find #"\.([0-9a-z]+)$" file))]
  (include-fn (str (str filetype "/")
                   file))))

(defn include-link [file include-fn]
  (include-fn file))

(def head {:title [:title "SICP in Clojure"]
           :js    (map (fn [file] (include-local file include-js)) 
                       (config :js))
           :css   (map (fn [file] (include-local file include-css)) 
                       (config :css))
           :fonts (map (fn [file] (include-link file include-font)) 
                       (config :fonts))})

(def header
  (html 
    [:p "Prev | Next"]
    [:p "+ Contents"]
    [:p "Dark"]))

(defn render [] 
  (html5
   (let [title (head :title)
         js    (head :js)
         css   (head :css)
         fonts (head :fonts)] 
    [:head 
       title
       js
       css
       fonts])
    [:body
      [:header header]
      [:div.content]]))
