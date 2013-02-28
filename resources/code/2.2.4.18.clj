(defn flip-vert [painter]  
  (transform-painter painter  
                     (make-vect 0.0 1.0)   ; new origin
                     (make-vect 1.0 1.0)   ; new end of edge1  
                     (make-vect 0.0 0.0))) ; new end of edge2  
