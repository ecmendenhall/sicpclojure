(ns links 
  (:require [clojure.string :as string]))

(defn fix-links! [page]
  (defn replace-links [match]
    (if (= "4.html" (nth match 2))
      (str "(contents.html#" 
           (nth match 3)
           ")") 
      (str "(" (nth match 1) ")")))

 (let [link-regex #"\(book-Z-H-(([0-9]\.html)#(sec_[0-9]+(:?\.[0-9])*))\)"
       page-text (slurp page)]
   (spit page (string/replace page-text link-regex replace-links))))
