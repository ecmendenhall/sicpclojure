(defn cube [x] (* x x x))  
(defn p [x] (- (* 3 x) (* 4 (cube x))))  
(defn sine [angle]  
   (if (not (> (Math/abs angle) 0.1))  
       angle  
       (p (sine (/ angle 3.0))))) 
