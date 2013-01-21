(defn pi-sum [a b]  
  (sum (fn [x] (/ 1.0 (* x (+ x 2))))  
       a  
       (fn [x] (+ x 4))  
       b)) 
