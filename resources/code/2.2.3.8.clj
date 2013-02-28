(defn enumerate-tree [tree]  
  (cond (empty? tree) nil  
        (not (pair? tree)) (list tree)  
        :else (append (enumerate-tree (car tree))  
                      (enumerate-tree (cdr tree)))))

(enumerate-tree (list 1 (list 2 (list 3 4)) 5))  
(1 2 3 4 5)  
