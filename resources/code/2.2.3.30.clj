(defn remove [item sequence]  
  (filter (fn [x] (not (= x item)))  
          sequence))  
