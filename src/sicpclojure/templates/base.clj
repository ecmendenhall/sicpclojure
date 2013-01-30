(ns sicpclojure.templates.base
  (:require [sicpclojure.config :as config])
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5, include-js, include-css]])
  (:require [clojure.string :as string])
  (:require [clojure.zip :as zip]))

(defn include-font 
  "Takes a font vector from config/templates, with optional size and style
  subvectors. Formats it as a Google Web Fonts URL. Returns a Hiccup :link element."
  [font]
  (let [font-name (string/replace (first font) " " "+")
        font-sizes (second (rest font))
        font-styles (last (rest font))]
    (include-css (str (config/templates :font-url)
                              font-name
                              (when font-sizes
                                (str ":"
                                     (string/join "," font-sizes)))
                              (when font-styles
                                (string/join (for [size font-sizes
                                                   style font-styles]
                                                  (str "," size style))))))))

(defn include-local 
  "Takes a vector of filenames from config/templates, the static directory path
  relative to the rendered template, and a Hiccup include function. Generates the
  correct path to the file and returns a Hiccup element based on the filetype."
  [file static-dir include-fn]
  (let [filetype (second (re-find #"\.([0-9a-z]+)$" file))] ; Matches filetype
  (include-fn (str static-dir
                   filetype 
                   "/"
                   file))))

(defn include-link 
  "Takes the URL to a remote script or stylesheet and a Hiccup include function.
  Returns a :link or :script element."
  [file include-fn]
  (include-fn file))

(defn make-head 
  "Generates a page header including the js, css, and fonts in config/templates."
  [static-dir]
  {:title [:title "SICP in Clojure"]
           :js    (conj (map (fn [file] (include-local file static-dir include-js))
                             (config/templates :js))
                        [:script {:data-main (str static-dir "js/main")
                                  :type "text/javascript"
                                  :src (str static-dir "js/require.js")}])
           :css   (map (fn [file] (include-local file static-dir include-css)) 
                       (config/templates :css))
           :fonts (map (fn [file] (include-link file include-font)) 
                       (config/templates :fonts))})

(def head (make-head (config/templates :static-dir)))

(defn make-footer 
  "Generates a page footer. Takes a path to the root directory, relative to the
  rendered template."
  [root-dir] 
  [:div 
   [:p 
    [:a {:href (str root-dir "about.html")} "About"]]
   [:p
    [:a {:href "http://creativecommons.org/licenses/by-sa/3.0/"}
     [:img {:src (str root-dir "static/img/licensebadge.png")
           :alt "CC-BY-NC 3.0"}]]]])

(def footer (make-footer "" ))

(defn render 
  "Returns the rendered Hiccup template as HTML5."
  [contents content] 
  (html5 {:lang "en"}
   (let [title (head :title)
         js    (head :js)
         css   (head :css)
         fonts (head :fonts)] 
    [:head
     [:meta {:charset "utf-8"}] 
       title
       js
       css
       fonts])
    [:body
      [:div.sidebar
        [:nav 
          [:p "<Prev  Next>"]
          contents
          [:p#colorscheme]]
        [:footer footer]]
      [:div.content content]]))
