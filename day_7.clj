(ns day-7)

(def input (map #(clojure.string/split % #" ") (clojure.string/split (slurp "temp4") #"\n")))

(defn parse-input-part-1 [] (reduce #(assoc %1 (first %2) (nthrest %2 4)) {} input))

(defn parse-input-part-2 [] (reduce #(assoc %1 (first %2) (rest %2)) {} input))

(defn run-part-1 []
      (let [input (parse-input-part-1)
            starting-position (first (first input))]
           (loop [position starting-position]
                 (let [next-position (first (keys (filter (fn [[k v]] (contains? (set v) position)) input)))]
                      (if (nil? next-position)
                        position
                        (recur next-position))))))

(defn map-data [val input]
      ())

#_(defn recursively-find-sums [val input weight]
      (let [parent-vals (get input val)
            next-children (rest parent-vals)
            parent-weight (Integer. (first parent-vals))

            childrens-weight (reduce #(conj %1 (Integer. (first (get input %2)))) [] next-children)
            unbalanced? (not= 1 (count (set childrens-weight)))
            #_ (println "childrens weight: " childrens-weight)]

           (if (and next-children unbalanced?)
             (do #_(println "parent-weight: " parent-weight)

                 (let [next-sums (reduce #(recursively-find-sums %2 input %1) 0 next-children)]
                      (if (nil? next-sums)
                        (do #_(println "stop!")
                            (reduce + parent-weight childrens-weight))
                        next-sums))))))
(defn find-sum [val input]
      (Integer. (first (get input val))))

(defn find-sums-for-children [vals input]
      (reduce #(+ (find-sum %2 input) %1) 0 vals))

(defn find-new-balance [first-children input]
      ; recursively find sums until you reach
      (println (->> first-children
                    (reduce #(conj %1 (get input %2)) [])
                    (reduce #(conj %1 (+ (Integer. (first %2)) (find-sums-for-children (rest %2) input))) [])))
      #_(reduce #(conj %1 (recursively-find-sums %2 input 0)) [] first-children))

(defn run-part-2 []
      (let [input (parse-input-part-2)
            parent (run-part-1)
            first-children (rest (get input parent))
            _ (println first-children)
            weights (find-new-balance first-children input)]
           weights))