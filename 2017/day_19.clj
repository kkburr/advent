(ns day-19)

(defn updown? [direction] (contains? #{"up" "down"} direction))

(defn find-next-pos [x y direction]
  (let [next-pos (condp = direction
                   "up"    [(- y 1) x]
                   "down"  [(+ y 1) x]
                   "left"  [y (- x 1)]
                   "right" [y (+ x 1)])]
    { :y (next-pos 0) :x (next-pos 1) :direction direction }))


(defn check-adjacents-for-direction [x y directions arrays]
  (loop [direction (first directions)
         directions (rest directions)]
    (if direction
      (let [{ :keys [x y] } (find-next-pos x y direction)
            value (nth (nth arrays y) x)]
        (if (= " " value)
          (recur (first directions) (rest directions))
          direction)))))

(defn find-turn-direction [direction arrays { :keys [x y]}]
  (let [directions (if (updown? direction) ["left" "right"] ["up" "down"])]
    (check-adjacents-for-direction x y directions arrays)))

(defn find-next-direc [{ :keys [direction arrays position] } next-val next-pos]
  (if (= "+" next-val)
      (find-turn-direction direction arrays next-pos)
      direction))

(defn move [{:keys [position value direction arrays] :as params} ]
  (let [{:keys [x y] :as next-pos} (find-next-pos (position 1) (position 0) direction)
	next-val (nth (nth arrays y) x)
        next-direc (find-next-direc params next-val next-pos)]
    {:next-pos [y x] :next-val next-val :next-direc next-direc}))

(defn next-spot [{:keys [position value direction arrays letters] :as params}]
  (loop [pos position
	 val value
	 direc direction
	 letterz letters
	 count 0]
	(if (= " " val) 
	    (str (clojure.string/join letterz) " " count)
	(let [{:keys [next-pos next-val next-direc] } (move {:position pos :value val :direction direc :arrays arrays :letters letterz})
	      next-letters (if (re-find #"\w" next-val) (conj letterz next-val) letterz)]
	  (recur next-pos next-val next-direc next-letters (+ 1 count)))))) 

(defn find-first-row [row]
  (loop [count 0]
    (if (= "|" (get row count))
        {:position [0 count] :value (get row count)}
        (recur (+ 1 count)))))

(defn run [arrays]
  (let [params (find-first-row (first arrays))]
    (next-spot (assoc params :direction "down" :arrays arrays :letters []))))

(defn main [filename]
  (let [input (slurp filename)
	array (clojure.string/split input #"\n")
	arrays (map #(clojure.string/split % #"") array)]
    (run arrays)))

