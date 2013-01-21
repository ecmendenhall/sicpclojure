(defn sqrt [x]  
  (fixed-point (average-damp (fn [y] (/ x y)))  
               1.0))
