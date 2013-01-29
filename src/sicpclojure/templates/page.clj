(ns sicpclojure.templates.page
  (:require [sicpclojure.config :as config])
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render head]]))

(def head (make-head "../static/"))

(defn make-header [contents page]
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
     [:p [:a {:href "contents.html"} "Contents"]]
     contents
     [:p#colorscheme ]]))

(defn render [contents content page] 
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
      (make-header contents page)
      [:div.content 
       [:div.chaptertext content]]]))
