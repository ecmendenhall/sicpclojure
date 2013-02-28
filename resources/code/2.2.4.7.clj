(defn square-limit [painter n]  
  (let [quarter (corner-split painter n)] 
    (let [half (beside (flip-horiz quarter) quarter))] 
      (below (flip-vert half) half))))
