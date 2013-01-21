(defn average-damp [f]  
  (fn [x] (average x (f x))))
