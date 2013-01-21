(defn sqrt [x]
  (fixed-point-of-transform
    (fn [y] (/ x y))
    average-damp
    1.0))
