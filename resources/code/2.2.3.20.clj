(defn fold-left [op initial sequence]  
  (defn iter [result more]  
    (if (empty? more)  
        result  
        (iter (op result (first more))  
              (rest more))))  
  (iter initial sequence))  
