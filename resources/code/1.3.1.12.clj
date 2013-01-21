(defn integral [f a b dx]  
  (defn add-dx [x] (+ x dx))  
  (* (sum f (+ a (/ dx 2.0)) add-dx b)  
     dx))  
(integral cube 0 1 0.01)  
0.24998750000000042  
(integral cube 0 1 0.001)  
0.249999875000001 
