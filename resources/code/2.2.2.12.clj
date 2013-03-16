(defn scale-tree [tree factor]  
  (cond (not (seq? tree)) (* tree factor)
        (empty? tree) '()  
        :else (cons (scale-tree (first tree) factor)  
                    (scale-tree (rest tree) factor))))

(scale-tree (list 1 (list 2 (list 3 4) 5) (list 6 7))  
            10)  
(10 (20 (30 40) 50) (60 70))
