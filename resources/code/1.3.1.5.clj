(defn sum [term a next-term b]  
  (if (> a b)  
      0  
      (+ (term a)  
         (sum term (next-term a) next-term b)))) 
