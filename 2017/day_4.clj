(ns day-4 (:require [clojure.string :as st]))

(defn run [filename]
  (let [blob (-> filename (slurp) (st/split #"\n"))
	passphrases (->> blob (reduce #(conj %1 (st/split %2 #" ")) []))]
    (reduce
	(fn [countt passphrase]
	  (if (= (count passphrase) (count (set passphrase)))
	      (+ countt 1)
	      countt))
	0
	passphrases)))

