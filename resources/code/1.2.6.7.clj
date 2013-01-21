(defn expmod [base exp m]  
  (remainder (fast-expt base exp) m))
