(defn subsets [s]  
  (if (empty? s)  
      '()  
      (let [more (subsets (cdr s))]  
        (append more (map <-??-> more)))))  
