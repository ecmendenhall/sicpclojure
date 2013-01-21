(defn sqrt [x]
  (fixed-point (fn [y] (/ (+ y (/ x y)) 2.0)) 1.0))
