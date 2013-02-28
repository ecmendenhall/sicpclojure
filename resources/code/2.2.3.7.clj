(defn enumerate-interval [low high]  
  (if (> low high)  
      nil  
      (cons low (enumerate-interval (+ low 1) high))))  

(enumerate-interval 2 7)  
(2 3 4 5 6 7)  
