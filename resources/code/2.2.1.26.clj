(defn square-list [items]  
  (defn iter [things answer]  
    (if (empty? things)  
        answer  
        (iter (rest things)   
              (cons (square (first things))  
                    answer))))  
  (iter items nil))
