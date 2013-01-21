(defn cube-root [x]  
  (fixed-point (average-damp (fn [y] (/ x (square y))))  
               1.0))
