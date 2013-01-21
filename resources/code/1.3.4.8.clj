(defn sqrt [x]
  (newtons-method
    (fn [y]
      (- (square y) x))
    1.0))
