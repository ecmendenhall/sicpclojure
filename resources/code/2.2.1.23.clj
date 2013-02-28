(defn scale-list [items factor]  
  (map (fn [x] (* x factor))  
       items))
