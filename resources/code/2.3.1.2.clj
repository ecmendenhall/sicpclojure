(* (+ 23 45) (+ x 9))  
  
(defn fact [n] (if (= n 1) 1 (* n (fact (- n 1)))))  
