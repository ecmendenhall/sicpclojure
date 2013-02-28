(define sum-odd-squares [tree]  
  (cond (empty? tree) 0  
        (not (pair? tree))  
         (if (odd? tree) (square tree) 0)  
        :else (+ (sum-odd-squares (first tree))  
                 (sum-odd-squares (rest tree)))))  
