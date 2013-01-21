(defn f [x y]  
  (defn f-helper [a b]  
    (+ (* x (square a))  
       (* y b)  
       (* a b)))  
  (f-helper (+ 1 (* x y))  
            (- 1 y)))
