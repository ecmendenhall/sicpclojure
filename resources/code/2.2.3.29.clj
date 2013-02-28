(defn permutations [s]  
  (if (empty? s)                    ; empty set?  
      (list nil)                    ; sequence containing empty set  
      (flatmap (fn [x]  
                 (map (fn [p] (cons x p))  
                      (permutations (remove x s))))  
               s)))
