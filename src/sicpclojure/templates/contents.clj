(ns sicpclojure.templates.contents
  (:require [sicpclojure.config :as config])  
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render head footer]]))

(def head (make-head "../static/"))

(def footer (make-footer "../static/"))

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
     [:div.sidebar
      [:nav 
       [:p "| " [:a {:href (str (first (config/build :complete)) ".html")} "Next"]]
       [:p#colorscheme]]
      [:footer footer]]
     [:div {:class "content" :id "toc"} contents]]))
