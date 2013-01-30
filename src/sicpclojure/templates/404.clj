(ns sicpclojure.templates.404
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render]]))

(def content
  (html
    [:h1 "Page not found"]
    [:p "We can't find the page you requested. If you clicked a link to a later
        chapter, it might not be ready yet."]))

(defn render [] 
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
       [:p [:a {:href "pages/contents.html"} "Contents"]]
       [:p#colorscheme ]]
      [:footer footer]]
     [:div.content content]]))
