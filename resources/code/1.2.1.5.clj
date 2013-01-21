(defn + [a b]  
  (if (= a 0)  
      b  
      (inc (+ (dec a) b))))  
  
(defn + [a b]  
  (if (= a 0)  
      b  
      (+ (dec a) (inc b))))
