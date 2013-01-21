(defn close-enough? [x y]  
  (< (Math/abs (- x y)) 0.001))
