(ns sicpclojure.templates.cover
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render]]))

(def content
  (html
    [:div.title 
      [:h1 "SICP In Clojure"]]
    [:div.quote
      [:h1 "&#8220;I personally don't think SICP will help you much with Clojure. YMMV.&#8221;"]
      [:p "&#8212;Rich Hickey, author, Clojure programming language"]]))

(defn render [] 
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
         [:p [:a {:href "pages/contents.html"} "Contents"]]
         [:p#colorscheme ]]
        [:footer footer]]
       [:div {:class "content span9 offset3"} content]]]]))
