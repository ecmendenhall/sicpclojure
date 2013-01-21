(defn fib [n]  
  (fib-iter 1 0 n))  
  
(defn fib-iter [a b counter]  
  (if (= counter 0)  
      b  
      (fib-iter (+ a b) a (- counter 1))))  
