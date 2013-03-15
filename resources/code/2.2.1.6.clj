(defn nth [items n]  
  (if (= n 0)  
      (first items)  
      (nth (rest items) (- n 1))))  

(def squares (list 1 4 9 16 25))  
  
=> (nth squares 3)  
16  
