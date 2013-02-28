(defn even-fibs [n]  
  (accumulate cons  
              nil  
              (filter even?  
                      (map fib  
                           (enumerate-interval 0 n)))))  
