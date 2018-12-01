(ns day-6)

(def input [4 1 15 12 0 9 9 5 5 8 7 3 14 5 12 3])

(defn find-next-index [index size]
      (if (= index (dec size))
        0
        (inc index)))

(defn redistribute [banks]
      (let [max-blocks (apply max banks)
            index (.indexOf banks max-blocks)
            banks (assoc banks index 0)
            banks-size (count banks)]
           (loop [counter max-blocks
                  index index
                  banks banks]
                 (if (pos? counter)
                   (let [next-index (find-next-index index banks-size)
                         next-banks (assoc banks next-index (inc (get banks next-index)))]
                        (recur (dec counter) next-index next-banks))
                   banks))))

(defn run-part-1 [input]
  (loop [banks input
         past-banks #{}
         counter 0]
        (if (and (pos? counter) (contains? past-banks banks))
          counter
          (recur (redistribute banks) (conj past-banks banks) (inc counter)))))

(defn run-part-2 [input]
      (loop [banks input
             past-banks []
             counter 0]
            (if (and (pos? counter) (not (empty? (filter #(= banks %) past-banks))))
              (- counter (.indexOf past-banks banks))
              (recur (redistribute banks) (conj past-banks banks) (inc counter)))))