(defn square [x] (* x x))  
  
(defn square [x]  
  (Math/exp (double-it (Math/log x))))  
  
(defn double-it [x] (+ x x)) 
