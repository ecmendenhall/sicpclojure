(ns sicpclojure.templates.contents
  (:require [sicpclojure.config :as config])  
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render head footer]]))

(def head (make-head "../static/"))

(def footer (make-footer "../"))

(defn render [contents] 
  (html5 {:lang "en"}
   (let [title (head :title)
         js    (head :js)
         css   (head :css)
         fonts (head :fonts)] 
    [:head
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}] 
       title
       js
       css
       fonts])
    [:body
     [:div.container
      [:div.row
       [:div {:class "sidebar span3"}
        [:nav 
         [:p "| " [:a {:href (str (first (config/build :complete)) ".html")} "Next"]]
         [:p#colorscheme]]
        [:footer footer]]
       [:div {:class "content span9 offset3" :id "toc"} contents]]]]))
