(ns sicpclojure.templates.about
  (:require [sicpclojure.config :as config])
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render]]))

(defn render [content] 
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
     [:div.content 
      [:div.chaptertext content]]]))
