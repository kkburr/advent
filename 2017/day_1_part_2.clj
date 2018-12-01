(ns day-1-part-2)

(defn match? [val1 val2] (= val1 val2))

(defn count-with-sum [val compare-val count]
  (if (match? val compare-val) (+ count (Integer. val)) count))

(defn find-halfway-position [position halfway-length array-length]
  (let [halfway-position (+ halfway-length position)]
    (if (> halfway-position array-length)
      (- halfway-position array-length)
      halfway-position)))

(defn run [number]
  (let [num-array (clojure.string/split (str number) #"")
	halfway (/ (count num-array) 2)]
    (loop [position 0
	   counter 0]
      (let [value (get num-array position)]
	(if-not value
	  counter
	  (let [halfway-position (find-halfway-position position halfway (count num-array))
                new-count (count-with-sum value (get num-array halfway-position) counter)]
	    (recur (+ 1 position) new-count)))))))
