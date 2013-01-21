(defn integral [f a b dx]  
  (* (sum f  
          (+ a (/ dx 2.0))  
          (fn [x] (+ x dx))  
          b)  
     dx))
