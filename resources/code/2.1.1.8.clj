(defn make-rat [n d]  
  (let [g (gcd n d)]  
    [(/ n g) (/ d g)]) 
