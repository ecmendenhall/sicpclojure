(defn horner-eval [x coefficient-sequence]  
  (accumulate (fn [this-coeff higher-terms] <-??->)  
              0  
              coefficient-sequence))
