(defn flipped-pairs [painter]  
  (let [painter2 (beside painter (flip-vert painter))] 
    (below painter2 painter2)))  
