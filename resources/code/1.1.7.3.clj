(defn sqrt-iter [guess x]  
  (if (good-enough? guess x)  
      guess  
      (sqrt-iter (improve guess x)  
                 x))) 
