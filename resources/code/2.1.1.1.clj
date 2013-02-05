(defn add-rat [x y]  
  (make-rat (+ (* (numer x) (denom y))  
               (* (numer y) (denom x)))  
            (* (denom x) (denom y))))

(defn sub-rat [x y]  
  (make-rat (- (* (numer x) (denom y))  
               (* (numer y) (denom x)))  
            (* (denom x) (denom y))))

(defn mul-rat [x y]  
  (make-rat (* (numer x) (numer y))  
            (* (denom x) (denom y))))

(defn div-rat [x y]  
  (make-rat (* (numer x) (denom y))  
            (* (denom x) (numer y))))

(defn equal-rat? [x y]  
  (= (* (numer x) (denom y))  
     (* (numer y) (denom x))))  
