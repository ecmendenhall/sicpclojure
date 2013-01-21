(defn newtons-method [g guess]
  (defn newton-transform [g]
    (fn [x]
      (- x (/ (g x)
              ((deriv g) x)))))
  (fixed-point (newton-transform g) guess))
