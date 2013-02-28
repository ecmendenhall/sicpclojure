(defn square-limit [painter n]  
  (let [combine4 (square-of-four flip-horiz identity  
                                  rotate180 flip-vert)]  
    (combine4 (corner-split painter n))))  
