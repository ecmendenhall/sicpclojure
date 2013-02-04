(defn half-interval-method [f a b]  
  (let [a-value (f a)  
        b-value (f b)]  
    (cond (and (negative? a-value) (positive? b-value))  
           (search f a b)  
          (and (negative? b-value) (positive? a-value))  
           (search f b a)  
          :else  
           (throw (Exception. "Values are not of opposite sign"))))) 
