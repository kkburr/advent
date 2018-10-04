(ns day-12 (:require [clojure.string :as st]
		     [clojure.set :as set]))

(defn find-next 
  [counter current-position been-there hash]
  (let [values (set/difference (set (get hash current-position)) been-there)
	val (first values)]
    (cond 
	(empty? values) counter  
	(> (count values) 1)
	(loop [val val
	       restt (rest values)
	       countt counter]
	  (if (nil? val)
	      countt
	      (recur (first restt) (rest restt) (find-next (+ 1 countt) val (conj been-there val) hash))))
	:else (find-next (+ 1 counter) val (conj been-there val) hash))))

(defn transform-to-hash [array]
  (reduce
    (fn [hasho strang]
      (let [row (st/split strang #" ")]
        (assoc hasho (first row) (rest row))))
    {}
    array))

(defn run [filename] 
  (->> (st/split (slurp filename) #"\n")
       (transform-to-hash)
       (find-next 1 "0" #{"0"})))
