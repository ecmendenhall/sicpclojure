(defn count [items]  
  (if (empty? items)  
      0  
      (+ 1 (count (rest items)))))  

(def odds (list 1 3 5 7))  
  
(count odds)  
4
