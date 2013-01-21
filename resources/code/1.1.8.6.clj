(defn sqrt [x]  
  (defn good-enough? [guess]  
    (< (abs (- (square guess) x)) 0.001))  
  (defn improve [guess]  
    (average guess (/ x guess)))  
  (defn sqrt-iter [guess]  
    (if (good-enough? guess)  
        guess  
        (sqrt-iter (improve guess))))  
  (sqrt-iter 1.0)) 
