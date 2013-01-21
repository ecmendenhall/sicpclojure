(defn identity [x] x)  
  
(defn sum-integers [a b]  
  (sum identity a inc b)) 
