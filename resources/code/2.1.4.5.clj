(defn make-center-width [c w]  
  (make-interval (- c w) (+ c w)))

(defn center [i]  
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width [i]  
  (/ (- (upper-bound i) (lower-bound i)) 2))
