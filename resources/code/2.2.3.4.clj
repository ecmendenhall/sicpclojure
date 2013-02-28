(defn filter [predicate sequence]  
  (cond (empty? sequence) nil  
        (predicate (rest sequence))  
         (cons (first sequence)  
               (filter predicate (rest sequence))))  
        :else (filter predicate (rest sequence)))  
