(ns day-2-part-2)

(defn find-quotient [array]
  (let [divisor-array (sort array)
	dividend-array (reverse divisor-array)]
     (reduce 
	(fn [counter dividend]
	  (if-not (> counter 0)  
	    (let [value 
	      (loop [divisor (first divisor-array)
	             divisor-array-2 (rest divisor-array)]
	  	(if (and (zero? (int (mod dividend divisor))) (not= dividend divisor))
	            (/ dividend divisor)
		    (if-not (empty? divisor-array-2)
	              (recur (first divisor-array-2) (rest divisor-array-2))
			counter)))]
	    (+ counter value))
	    counter))
	0 
	dividend-array)))

(defn run [arrays]
  (loop [array (first arrays)
	 arrays (rest arrays)
	 counter 0]
  (if (empty? array)
       counter
       (let [quotient (find-quotient array)]
         (recur (first arrays) (rest arrays) (+ counter quotient))))))
