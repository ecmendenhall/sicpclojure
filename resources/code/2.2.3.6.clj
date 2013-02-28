(defn accumulate [op initial sequence]  
  (if (empty? sequence)  
      initial  
      (op (first sequence)  
          (accumulate op initial (rest sequence)))))

(accumulate + 0 (list 1 2 3 4 5))  
15 

(accumulate * 1 (list 1 2 3 4 5))  
120 

(accumulate cons nil (list 1 2 3 4 5))  
(1 2 3 4 5)  
