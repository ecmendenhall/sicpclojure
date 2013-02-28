(defn subsets [s]  
  (if (null? s)  
      (list nil)  
      (let [more (subsets (cdr s))]  
        (append more (map <-??-> more)))))  
