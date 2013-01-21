(defn deriv [g]
  (let [dx 0.00001]
  (fn [x]
    (/ (- (g (+ x dx)) (g x))
       dx))))
