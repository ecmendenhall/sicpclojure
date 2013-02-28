(defn prime-sum? [pair]  
  (prime? (+ (first pair) (first (rest pair)))))  
