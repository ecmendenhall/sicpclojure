(defn enumerate-tree [tree]  
  (cond (not (seq? tree)) (list tree)
        (empty? tree) nil  
        :else (concat (enumerate-tree (first tree))  
                      (enumerate-tree (rest tree)))))

(enumerate-tree (list 1 (list 2 (list 3 4)) 5))  
(1 2 3 4 5)  
