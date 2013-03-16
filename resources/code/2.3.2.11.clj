(defn deriv [exp v]  
  (cond (number? exp) 0  
        (variable? exp)  
         (if (same-variable? exp v) 1 0)  
        (sum? exp)  
         (make-sum (deriv (addend exp) v)  
                   (deriv (augend exp) v))  
        (product? exp)  
         (make-sum  
           (make-product (multiplier exp)  
                         (deriv (multiplicand exp) v))  
           (make-product (deriv (multiplier exp) v)  
                         (multiplicand exp))))  
        (else  
         (error "unknown expression type \-- DERIV" exp)))  
