(defn f [x y]  
  ((fn [a b]  
     (+ (* x (square a))  
        (* y b)  
        (* a b)))  
   (+ 1 (* x y))  
   (- 1 y)))
