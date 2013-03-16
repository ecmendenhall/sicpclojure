(defn scale-tree [tree factor]  
  (map (fn [sub-tree]  
         (if (seq? sub-tree)  
             (scale-tree sub-tree factor)  
             (* sub-tree factor)))  
       tree))  
