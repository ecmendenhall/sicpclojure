(def zero (fn [f] (fn [x] x)))  
  
(def add-1 [n]  
  (fn [f] (fn [x] (f ((n f) x)))))
