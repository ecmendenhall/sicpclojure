(defn map [p sequence]  
  (accumulate (fn [x y] <-??->) nil sequence))

(defn append [seq1 seq2]  
  (accumulate cons <-??-> <-??->))

(defn length [sequence]  
  (accumulate <-??-> 0 sequence))  
