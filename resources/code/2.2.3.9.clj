(defn sum-odd-squares [tree]  
  (accumulate +  
              0
              (map square  
                   (filter odd?  
                           (enumerate-tree tree)))))  
