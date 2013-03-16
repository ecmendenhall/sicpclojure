(defn concat [list1 list2]  
  (if (empty? list1)  
      list2  
      (cons (first list1) 
            (concat (rest list1) list2))))  
