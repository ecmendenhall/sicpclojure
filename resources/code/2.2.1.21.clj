(defn scale-list [items factor]  
  (if (empty? items)  
      nil  
      (cons (* (first items) factor)  
            (scale-list (rest items) factor))))  

(scale-list (list 1 2 3 4 5) 10)  
(10 20 30 40 50)
