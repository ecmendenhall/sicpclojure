(ns sicpclojure.templates.page
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.core :exclude [config]])
  (:use [sicpclojure.templates.base :exclude [render]]))

(def content (get-page "resources/text/10.md"))

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
