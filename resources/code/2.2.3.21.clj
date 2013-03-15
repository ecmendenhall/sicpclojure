(defn fold-left [op initial items]
  (defn iter [result more]
    (if (empty? more)
        result
        (iter (op result (first more))
              (rest more))))
  (iter initial items))
