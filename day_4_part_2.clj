(ns day-4-part-2 (:require [clojure.string :as st]))

(defn non-anagrams [passphrase]
  (set (reduce #(conj %1 (st/join (sort %2))) [] passphrase)))

(defn run [filename]
  (let [blob (-> filename (slurp) (st/split #"\n"))
	passphrases (->> blob (reduce #(conj %1 (st/split %2 #" ")) []))]
    (reduce
	(fn [countt passphrase]
	  (if (= (count passphrase) (count (set passphrase)) (count (non-anagrams passphrase)))
	      (+ countt 1)
	      countt))
	0
	passphrases)))

