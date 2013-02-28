(flatmap  
 (fn [new-row]  
   (map (fn [rest-of-queens]  
          (adjoin-position new-row k rest-of-queens))  
        (queen-cols (- k 1))))  
 (enumerate-interval 1 board-size))  
