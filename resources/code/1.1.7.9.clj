(defn new-if [predicate then-clause else-clause]  
  (cond (predicate) then-clause  
        :else else-clause)) 
