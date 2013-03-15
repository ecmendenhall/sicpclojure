(def x (list (list 1 2) (list 3 4)))  
  
=> x  
((1 2) (3 4))  
  
=> (reverse x)  
((3 4) (1 2))  
  
=> (deep-reverse x)  
((4 3) (2 1))  
