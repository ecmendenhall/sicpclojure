(defn vector [x y]  
  (defn dispatch [m]  
    (cond (= m 0) x  
          (= m 1) y  
          :else (throw (Exception. "Argument not 0 or 1" ))))  
  dispatch)
  
(defn first [z] (z 0))  
  
(defn second [z] (z 1)) 
