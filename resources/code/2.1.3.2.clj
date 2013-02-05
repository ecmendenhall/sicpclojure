(defn cons [x y]  
  (defn dispatch [m]  
    (cond (= m 0) x  
          (= m 1) y  
          :else (throw (Exception. "Argument not 0 or 1" ))))  
  dispatch)
  
(defn car [z] (z 0))  
  
(defn cdr [z] (z 1)) 
