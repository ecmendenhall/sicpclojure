(ns sicpclojure.templates.contents
  (:require [sicpclojure.config :as config])  
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render head]]))

(def head (make-head "../static/"))

(defn render [contents] 
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
      [:header 
       [:p "| " [:a {:href (str (first (config/build :complete)) ".html")} "Next"]]
       [:p#colorscheme]]
      [:div {:class "content" :id "toc"} contents]]))
