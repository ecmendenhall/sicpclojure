(defn (sqrt x)  
  (fixed-point (fn [y] (/ x y))  1.0)) 
