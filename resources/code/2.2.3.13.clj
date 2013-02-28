(defn salary-of-highest-paid-programmer [records]  
  (accumulate max  
              0  
              (map salary  
                   (filter programmer? records))))  
