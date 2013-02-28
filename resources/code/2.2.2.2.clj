(def x (cons (list 1 2) (list 3 4)))  
  
(count x)  
3

(count-leaves x)  
4  
  
(list x x)  
(((1 2) 3 4) ((1 2) 3 4))  
  
(count (list x x))  
2  
  
(count-leaves (list x x))  
8  
