(ns day-1)

(defn match? [val1 val2] (= val1 val2))

(defn count-with-sum [val compare-val count]
  (if (match? val compare-val) (+ count (Integer. val)) count))

(defn run [number]
  (let [num-array (clojure.string/split (str number) #"")]
    (loop [position 1
	   count 0]
      (let [value (get num-array position)]
        (if-not value
          (count-with-sum (get num-array (- position 1)) (get num-array 0) count)
          (recur (+ 1 position) (count-with-sum value (get num-array (- position 1)) count)))))))
