(defn transform-painter [painter origin corner1 corner2]
  (fn [frame]  
    (let [m (frame-coord-map frame)]
      (let [new-origin (m origin)]  
        (painter  
         (make-frame new-origin  
                     (sub-vect (m corner1) new-origin)  
                     (sub-vect (m corner2) new-origin)))))))  
