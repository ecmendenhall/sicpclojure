(defn dot-product [v w]  
  (accumulate + 0 (map * v w)))  
