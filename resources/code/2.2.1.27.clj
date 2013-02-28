(defn square-list [items]  
  (defn iter [things answer]  
    (if (empty? things)  
        answer  
        (iter (rest things)  
              (cons answer  
                    (square (first things))))))  
  (iter items nil))
