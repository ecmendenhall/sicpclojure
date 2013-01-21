(defn expt [b n]  
  (expt-iter b n 1))  
  
(defn expt-iter [b counter product]  
  (if (= counter 0)  
      product  
      (expt-iter b  
                (- counter 1)  
                (* b product)))) 
