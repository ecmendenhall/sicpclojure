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
     [:div.container
      [:div.row
       [:div {:class "sidebar span3"}
        [:nav
         [:p [:a {:href "pages/contents.html"} "Contents"]]
         [:p#colorscheme ]] 
        [:footer footer]]
       [:div {:class "content span9 offset3"}
        [:div.chaptertext content]]]]]))
