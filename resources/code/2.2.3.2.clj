(defn even-fibs [n]  
  (defn next [k]  
    (if (> k n)  
        nil  
        (let [f (fib k)]  
          (if (even? f)  
              (cons f (next (+ k 1)))  
              (next (+ k 1))))))  
  (next 0))  
