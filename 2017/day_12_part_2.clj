(ns day-12-part-2 (:require [clojure.string :as st]
		     [clojure.set :as set]))

(defn find-visited-positions 
  [current-position been-there hash]
  (let [values (set/difference (set (get hash current-position)) been-there)
	val (first values)]
    (cond 
	(empty? values) been-there  
	(> (count values) 1)
	(loop [val val
	       restt (rest values)
	       been-there been-there]
	  (if (nil? val)
	      been-there
	      (recur (first restt) (rest restt) (find-visited-positions val (conj been-there val) hash))))
	:else (find-visited-positions val (conj been-there val) hash))))

(defn find-total-programs [hash]
    (loop [position "0"
  	   restt (rest hash)
	   counter 1
	   visited (find-visited-positions "0" #{"0"} hash)]
      (if (nil? position)
	counter 
	(let [in-program (contains? visited position)
	      new-counter (if in-program counter (+ 1 counter))
	      new-visited (if in-program visited (find-visited-positions position visited hash))] 
	  (recur (first (first restt)) (rest restt) new-counter new-visited)))))

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
       find-total-programs))
