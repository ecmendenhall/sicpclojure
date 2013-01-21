(defn sqrt [x]
  (fixed-point-of-transform
    (fn [y] (- (square y) x))
    newton-transform
    1.0))
