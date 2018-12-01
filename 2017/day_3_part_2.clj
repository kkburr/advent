(ns day-3-part-2)

(defn getUp [y x] [(- y 1) x])

(defn getDown [y x] [(+ y 1) x])

(defn getLeft [y x] [y (- x 1)])

(defn getRight [y x] [y (+ x 1)])

(defn getDiagnalUpRight [y x] [(- y 1) (+ x 1)])

(defn getDiagnalUpLeft [y x] [(- y 1) (- x 1)])

(defn getDiagnalDownLeft [y x] [(+ y 1) (- x 1)])

(defn getDiagnalDownRight [y x] [(+ y 1) (+ x 1)])

(def movement-pattern [getUp getRight getDown getLeft])

(def calculation-pattern [getUp getDown getLeft getRight getDiagnalUpRight getDiagnalUpLeft getDiagnalDownLeft getDiagnalDownRight])

(def initial-grid [[0 0 0] [0 1 0] [0 0 0]])

(defn find-next-position [position movement]
      (if movement
        (apply movement position)
        position))

(defn calculate [grid position]
      (->> (map #(get-in grid (apply % position)) calculation-pattern)
           (filter #(not (nil? %)))
           (reduce + 0)))

(defn find-next-grid [position value grid]
      (update-in grid position + value))

(defn outOfBounds? [grid current-position movement]
      (nil? (get-in grid (find-next-position current-position movement))))

(defn increase-grid [grid]
      (let [widened-grid (vec (map #(vec (cons 0 (conj % 0))) grid))
            row-length (count (first widened-grid))
            new-row (vec (take row-length (repeat 0)))]
           (vec (cons new-row (conj widened-grid new-row)))))

(defn run [number]
      (if (= 1 number)
        1
        (loop [position [1 0]
               outer-length 8
               grid initial-grid
               row-number 0]
              (let [data (loop [position position
                                counter 1
                                grid grid
                                path movement-pattern]
                               (let [pointer-value (calculate grid position)
                                     next-grid (find-next-grid position pointer-value grid)
                                     next-path (if (outOfBounds? grid position (first path)) (rest path) path)
                                     next-position (find-next-position position (first next-path))]
                                    (cond
                                      (> pointer-value number) pointer-value
                                      (>= counter outer-length) {:position position :grid next-grid}
                                      :else
                                      (recur next-position (+ 1 counter) next-grid next-path))))]
                   (if (number? data)
                     data
                     (let [next-grid (increase-grid (get data :grid))
                           next-position [(- (count next-grid) 2) 0]]
                          (recur next-position (+ outer-length 8) next-grid (+ 1 row-number))))))))