(defn count-leaves [x]
  (cond (not (seq? x)) 1
        (empty? x) 0  
        :else (+ (count-leaves (first x))  
                 (count-leaves (rest x)))))
