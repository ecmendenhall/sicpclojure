(defn queens [board-size]  
  (defn queen-cols [k]  
    (if (= k 0)  
        (list empty-board)  
        (filter  
         (fn [positions] (safe? k positions))  
         (flatmap  
          (fn (rest-of-queens)  
            (map (fn [new-row]  
                   (adjoin-position new-row k rest-of-queens))  
                 (enumerate-interval 1 board-size)))  
          (queen-cols (- k 1))))))  
  (queen-cols board-size))  
