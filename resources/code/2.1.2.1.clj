(defn make-rat [n d]
  [n d])

(defn numer [x]  
  (let [g (gcd (first x) (second x))]  
    (/ (first x) g)))

(defn denom [x]  
  (let [g (gcd (first x) (second x))]  
    (/ (second x) g)))
