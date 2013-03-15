(ns sicpclojure.colorscheme
  (:require [jayq.core :as jq]))

(defn reverse-scheme [scheme]
  (if (= "Dark") "Light" "Dark"))

(defn set-scheme [color]
  (let [a (jq/$ :#currentcolor)]
    (if (= "Dark" color)
      (do
        (jq/text (jq/$ a) "Light")
        (jq/attr (jq/$ :html) "class" "dark")
        (.setItem js/localStorage "colorscheme" "Dark"))
      (do
        (jq/text (jq/$ a) "Dark")
        (jq/attr (jq/$ :html) "class" "")
        (.setItem js/localStorage "colorscheme" "Light")))))

(defn change-scheme []
  (let [a (jq/$ :#currentcolor)]
    (if (= "Dark" (jq/text a))
      (set-scheme "Dark")
      (set-scheme "Light"))))

(defn create-scheme-anchor []
  (let [color-p (jq/$ :#colorscheme)
        color-a (.createElement js/document "a")]
    (->
      (jq/$ color-a)
      (jq/attr "id" "currentcolor")
      (jq/bind "click" change-scheme))
    (jq/append color-p color-a)))

(defn get-colors []
  (let [color-pref (.getItem js/localStorage "colorscheme")]
    (create-scheme-anchor)
    (set-scheme color-pref)))

(jq/document-ready (fn [] 
                     (get-colors)))
