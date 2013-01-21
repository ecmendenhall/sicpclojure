(defn f [x y]  
  (let [a (+ 1 (* x y))  
        b (- 1 y)]  
    (+ (* x (square a))  
       (* y b)  
       (* a b))))
