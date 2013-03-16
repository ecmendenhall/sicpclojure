(defn memq [item x]  
  (cond ((empty? x) false)  
        ((eq? item (first x)) x)  
        (else (memq item (rest x)))))  
