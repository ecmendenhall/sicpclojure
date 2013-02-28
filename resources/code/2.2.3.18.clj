(defn accumulate-n [op init seqs]  
  (if (nil? (first seqs))  
      nil  
      (cons (accumulate op init <-??->)  
            (accumulate-n op init <-??->))))  
