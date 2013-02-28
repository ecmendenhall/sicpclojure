(accumulate concat  
            nil  
            (map (fn [i]  
                   (map (fn [j] (list i j))  
                        (enumerate-interval 1 (- i 1))))  
                 (enumerate-interval 1 n)))
