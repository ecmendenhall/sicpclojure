(defn expmod [base exp m]
  (cond (= exp 0) 1
        (even? exp) (mod (* (expmod base (/ exp 2) m)
                            (expmod base (/ exp 2) m)) m)
        :else (mod (*' base (expmod base (-' exp 1) m)) m)))
