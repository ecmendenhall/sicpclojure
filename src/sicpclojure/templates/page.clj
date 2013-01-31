(ns sicpclojure.templates.page
  (:require [sicpclojure.config :as config])
  (:require [hiccup.core :refer [html]])
  (:require [hiccup.page :refer [html5]])
  (:use [sicpclojure.templates.base :exclude [render head footer]]))

(def head (make-head "../static/"))

(def footer (make-footer "../"))

(defn page-src [page]
  (str (config/build :repo-url) "blob/master/resources/text/" page ".md"))

(defn make-nav 
  "Takes a Hiccup vector as page contents and a page number. Generates a Hiccup :nav
  element based on the number of completed pages listed in config/build."
  [contents page]
  (let [prev-page (if (< (dec page) (first (config/build :complete)))
                    "../index.html"
                    (str (dec page) ".html"))
        next-page (if (> (inc page) (last (config/build :complete)))
                    false
                    (str (inc page) ".html"))]
    [:nav 
     [:p 
      [:a {:href prev-page} "Prev"]
      " | "
      (when next-page [:a {:href next-page} "Next"])]
     [:p [:a {:href "contents.html"} "Contents"]]
     contents
     [:p#colorscheme]]))

(defn render [contents content page] 
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
          (make-nav contents page)
          [:footer footer]]
        [:div {:class "content span9 offset3"}
        [:a {:href (page-src page) :class "text-source-link"} "Page text"]
        [:div.chaptertext content]]]]]))
