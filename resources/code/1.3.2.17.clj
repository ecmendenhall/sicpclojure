(defn f [x y]  
  (def a (+ 1 (* x y)))  
  (def b (- 1 y))  
  (+ (* x (square a))  
     (* y b)  
     (* a b)))
