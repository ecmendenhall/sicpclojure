(defn good-enough? [guess x]  
  (< (abs (- (square guess) x)) 0.001)) 
