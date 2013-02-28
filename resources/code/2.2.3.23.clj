(defn reverse [sequence]  
  (fold-right (fn [x y] <-??->) nil sequence))

(defn reverse [sequence]  
  (fold-left (fn [x y] <-??->) nil sequence))  
