(ns day-2)

(defn run [arrays]
  (loop [array (first arrays)
	 arrays (rest arrays)
	 counter 0]
    (if (empty? array)
	counter
	(let [sorted-array (sort array)
	      new-count (+ counter (- (last sorted-array) (first sorted-array)))]
           (recur (first arrays) (rest arrays) new-count)))))
