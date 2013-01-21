(defn fib [n]  
  (fib-iter 1 0 0 1 n))  
(defn fib-iter [a b p q counter]  
  (cond ((= counter 0) b)  
        ((even? counter)  
         (fib-iter a  
                   b  
                   <??>      ; compute p'  
                   <??>      ; compute q'  
                   (/ count 2)))  
        (else (fib-iter (+ (* b q) (* a q) (* a p))  
                        (+ (* b p) (* a q))  
                        p  
                        q  
                        (- count 1))))) 
