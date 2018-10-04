(ns day-3)

(defn calculate [])

(defn run [number]
  (if (= 1 number) 
    0
    (loop [pointer 1
	 				 row-length 0
					 to-add 8
					 rows [1]
					 row-number 0]
			(if (> number pointer)
				(let [next-pointer (+ to-add pointer)]
					(recur next-pointer (+ row-length 2) (+ to-add 8) (conj rows next-pointer) (+ 1 row-number)))
						(let [shortest-points (range (+ row-number (last (pop rows))) (last rows) row-length)]
							(loop [point (first shortest-points)
										 points (rest shortest-points)]
								(let [difference (Math/abs (- point number))]
									(cond
										(= point number) row-number
										(< difference (/ row-length 2)) (+ difference row-number)
										:else (recur (first points) (rest points))))))))))
