(defn flatmap [proc seq]  
  (accumulate append nil (map proc seq)))  
