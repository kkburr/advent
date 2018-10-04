(ns boggle)

(def lib {"a"
          {"n" {"d" 0 "t" {0 true "e" 0} "g" {"r" {"y" 0}}}
           "u" {"n" {"t" 0}}}})

(def board
  {[0 0] "z" [0 1] "m" [0 2] "l" [0 3] "y"
   [1 0] "t" [1 1] "m" [1 2] "q" [1 3] "e"
   [2 0] "n" [2 1] "a" [2 2] "r" [2 3] "t"
   [3 0] "p" [3 1] "u" [3 2] "n" [3 3] "n"})

(defn find-adjacents [[y x :as pos]]
      (if-not (nil? pos)
              (list [(+ 1 y) (+ 1 x)]
                    [(+ 1 y) x]
                    [(+ 1 y) (- x 1)]
                    [(- y 1) x]
                    [(- y 1) (+ 1 x)]
                    [(- y 1) (- x 1)]
                    [y (+ 1 x)]
                    [y (- x 1)])))

(defn search-for-word [position board words string library]
      (let [to-search (find-adjacents position)]
           (loop [pos (first to-search) go (rest to-search) words words board board library lib string string]
                 (if (nil? pos)
                   words
                   (let [letter (get board pos)
                         dict-find (get library letter)
                         is-word (get dict-find 0)
                         current-word (str string letter)
                         words (if-not (nil? is-word) (conj words current-word) words)]
                        (if (nil? dict-find)
                          (recur (first rest) (rest go) words board library string)
                          (search-for-word pos (dissoc board pos) words current-word dict-find)))))))

(defn walk-across-board []
      (let [length (count (keys board))
            i (Math/sqrt length)]
           (loop [x 0 words []]
                 (if (< x i)
                   (let [words (loop [y 0 words words]
                                     (if (< y i)
                                       (let [current [x y]
                                             words (search-for-word current (dissoc board current) words "" lib)]
                                            (recur (+ 1 y) words))
                                       words))]
                        (recur (+ 1 x) words))
                   words))))
