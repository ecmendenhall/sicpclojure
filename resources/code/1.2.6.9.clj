(defn factorial [n]
  (defn fact-iter [product counter]  
    (if (> counter n)  
        product  
        (fact-iter (* counter product)  
                   (+ counter 1)  
                   max-count)))   
  (fact-iter 1 1))  
