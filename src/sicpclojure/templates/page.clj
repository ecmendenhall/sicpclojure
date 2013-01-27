(ns sicpclojure.templates.page
  (:require [sicpclojure.config :as config])
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render head header]]))

(def head (make-head "../static/"))

(defn make-header [page]
  (let [prev-page (if (< (dec page) (first (config/build :complete)))
                    "../index.html"
                    (str (dec page) ".html"))
        next-page (if (> (inc page) (last (config/build :complete)))
                    false
                    (str (inc page) ".html"))]
    [:header 
     [:p 
      [:a {:href prev-page} "Prev"]
      " | "
      (when next-page [:a {:href next-page} "Next"])]
     [:p "+ Contents"]
     [:p#colorscheme ]]))

(defn render [content page] 
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
      (make-header page)
      [:div.content content]]))
