(ns sicpclojure.templates.cover
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [header render]]))

(def header
  (html 
    [:p "+ Contents"]
    [:p "Dark"]))

(def content
  (html
    [:div.title 
      [:h1 "SICP In Clojure"]]
    [:div.quote
      [:h1 "&#8220;I personally don't think SICP will help you much with Clojure. YMMV.&#8221;"]
      [:p "&#8212;Rich Hickey, author, Clojure programming language"]]))

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
      [:div.content content]]))
