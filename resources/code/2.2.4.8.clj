(defn square-of-four [tl tr bl br]  
  (fn [painter]  
    (let [top (beside (tl painter) (tr painter))
          bottom (beside (bl painter) (br painter))]
      (below bottom top))))
