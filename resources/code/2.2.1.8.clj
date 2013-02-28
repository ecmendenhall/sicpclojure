(defn count [items]  
  (defn count-iter [a length]  
    (if (empty? a)  
        length  
        (count-iter (rest a) (+ 1 length))))  
  (count-iter items 0))  
