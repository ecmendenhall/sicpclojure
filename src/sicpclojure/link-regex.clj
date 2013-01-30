(ns links 
  (:require [clojure.string :as string]))

;; A one-off script that cleans up links in the source text markdown files.
(defn fix-links! [page]
  (defn replace-links [match]
    (if (= "4.html" (nth match 2))
      (str "(contents.html#" 
           (nth match 3)
           ")") 
      (str "(" (nth match 1) ")")))
 
      ;; Matches the crazy links in the original SICP pages:
      ;; "book-Z-H-(n).html#sec_(n)"   
 (let [link-regex #"\(book-Z-H-(([0-9]\.html)#(sec_[0-9]+(:?\.[0-9])*))\)"
       page-text (slurp page)]
   (spit page (string/replace page-text
                              link-regex 
                              replace-links))))

(map fix-links! (map (fn [n] (str "resources/text/" n ".md")) (range 9 39))
