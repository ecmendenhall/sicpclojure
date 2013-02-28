(defn flipped-pairs [painter]  
  (let [combine4 (square-of-four identity flip-vert  
                                 identity flip-vert)] 
    (combine4 painter)))
