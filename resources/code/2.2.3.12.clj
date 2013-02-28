(defn product-of-squares-of-odd-elements [sequence]  
  (accumulate *  
              1  
              (map square  
                   (filter odd? sequence)))) 

(product-of-squares-of-odd-elements (list 1 2 3 4 5))  
225  
