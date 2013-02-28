(defn squash-inwards [painter]  
  (transform-painter painter  
                     (make-vect 0.0 0.0)  
                     (make-vect 0.65 0.35)  
                     (make-vect 0.35 0.65)))  
