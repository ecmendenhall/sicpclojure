(defn prime-sum-pairs [n]  
  (map make-pair-sum  
       (filter prime-sum?  
               (flatmap  
                (fn [i]  
                  (map (fn [j] (list i j))  
                       (enumerate-interval 1 (- i 1))))  
                (enumerate-interval 1 n)))))
