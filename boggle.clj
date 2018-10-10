(ns boggle)

(def sample-lib {"a"
                 {"n" {"d" 0 "t" {0 0 "e" 0} "g" {"r" {"y" 0}}}
                  "u" {"n" {"t" 0}}}})

(def sample-board
  {[0 0] "z" [0 1] "m" [0 2] "l" [0 3] "y"
   [1 0] "t" [1 1] "m" [1 2] "q" [1 3] "e"
   [2 0] "n" [2 1] "a" [2 2] "r" [2 3] "t"
   [3 0] "p" [3 1] "u" [3 2] "n" [3 3] "n"})

(defn is-word [x] (or (= 0 x) (get x 0)))
(defn lib-find [letter lib] (get lib letter))
(defn in-board [board position] (if (get board position) position))

(defn board-find [board position] (get board position))

(defn surrounding-squares [[y x :as pos]]
      (list [(+ 1 y) (+ 1 x)]
            [(+ 1 y) x]
            [(+ 1 y) (- x 1)]
            [(- y 1) x]
            [(- y 1) (+ 1 x)]
            [(- y 1) (- x 1)]
            [y (+ 1 x)]
            [y (- x 1)]))

(defn find-adjacents
  [position]
  (->> (surrounding-squares position)
      (map #(in-board sample-board %))
      (remove nil?)))

(defn add-word
      [pointer word]
      (assoc-in pointer [:words] (conj (get pointer :words) word)))

(defn add-letter
      [pointer letters]
      (assoc-in pointer [:string] letters))

(defn change-lib
      [pointer lib]
      (assoc-in pointer [:lib] lib))

(defn combine-words
      [pointer set]
      (assoc-in pointer [:words] (into (get pointer :words) set)))

(defn traverse
      [position1 board pointer1]
      (reduce
        (fn [{words :words string :string lib :lib, :as pointer2} position2]
            (let [letter (board-find board position2)
                  match (lib-find letter lib)
                  next-word (str string letter)]
                 (cond
                   (nil? match)
                   pointer2

                   (and (is-word (get match 0)) (map? match))
                   (combine-words pointer2 (get (traverse position2 (dissoc board position2) (change-lib (add-word (add-letter pointer2 next-word) next-word) match)) :words))

                   (map? match)
                   (combine-words pointer2 (get (traverse position2 (dissoc board position2) (change-lib (add-letter pointer2 next-word) match)) :words))

                   (is-word match)
                   (add-word pointer1 next-word))))
        pointer1
        (-> (find-adjacents position1))))

(defn walk-board
      []
      (reduce-kv
        #(get (traverse %2 (dissoc sample-board %2) {:words %1 :string %3 :lib (lib-find %3 sample-lib)}) :words)
        #{}
        sample-board))