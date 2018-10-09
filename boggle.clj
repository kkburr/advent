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

(defn search-for-words [words position string library board]
      (let [to-search (find-adjacents position)]
           (loop [pos (first to-search) go (rest to-search) words words board board library library string string]
                 (if (nil? pos)
                   words
                   (let [letter (board-find board pos)
                         next-lib (lib-find letter library)
                         current (str string letter)
                         words (if (is-word next-lib) (conj words current) words)]
                        (if (nil? next-lib)
                          (recur (first go) (rest go) words board library string)
                          (recur (first go) (rest go) (search-for-words words pos current next-lib (dissoc board pos)) board library string)))))))

(defn walk-board
      []
      (reduce-kv
        #(search-for-words %1 %2 %3 (lib-find %3 sample-lib) (dissoc sample-board %2))
        #{}
        sample-board
        ))