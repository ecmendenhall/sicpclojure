(defn factorial [n]  
  (fact-iter 1 1 n))  
  
(defn fact-iter [product counter max-count]  
  (if (> counter max-count)  
      product  
      (fact-iter (* counter product)  
                 (+ counter 1)  
                 max-count)))  

