(defn make-pair-sum [pair]  
  (list (first pair) 
        (first (rest pair)) 
        (+ (first pair) (first (rest pair)))))  
