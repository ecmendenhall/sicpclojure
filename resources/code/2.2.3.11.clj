(defn list-fib-squares [n]  
  (accumulate cons  
              nil  
              (map square  
                   (map fib  
                        (enumerate-interval 0 n)))))  

(list-fib-squares 10)  
(0 1 1 4 9 25 64 169 441 1156 3025)  
