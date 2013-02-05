(defn vector [x y]  
  (fn [m] (m x y)))  
  
(defn first [z]  
  (z (fn [p q] p))) 
