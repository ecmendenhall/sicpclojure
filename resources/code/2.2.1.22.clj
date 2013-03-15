(defn map [proc items]
  (if (empty? items)
      nil
      (list (proc (first items))
            (map proc (rest items)))))

=> (map abs (list -10 2.5 -11.6 17))
(10 2.5 11.6 17)

=> (map (fn [x] (* x x))
        (list 1 2 3 4))
(1 4 9 16)
