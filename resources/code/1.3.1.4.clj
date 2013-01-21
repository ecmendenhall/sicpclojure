(defn <name> [a b]  
  (if (> a b)  
      0  
      (+ (<term> a)  
         (<name> (<next> a) b)))) 
