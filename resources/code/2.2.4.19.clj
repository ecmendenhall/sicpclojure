(defn shrink-to-upper-right [painter]  
  (transform-painter painter  
                     (make-vect 0.5 0.5)  
                     (make-vect 1.0 0.5)  
                     (make-vect 0.5 1.0)))  
