(defn segments->painter [segment-list]  
  (fn [frame]  
    (for-each  
     (fn [segment]  
       (draw-line  
        ((frame-coord-map frame) (start-segment segment))  
        ((frame-coord-map frame) (end-segment segment))))  
     segment-list)))
