(ns clojure-noob.peg-game
  (require [clojure.set :as set])
  (:gen class))

(declare successful-move prompt-move game-over query-rows)

(defn tri*
  ([] (tri* 0 1))
  ([sum n]
   (let [new_sum (+ sum n)]
     (cons new_sum (lazy-seq (tri* new_sum (inc n)))))))


(defn triangular? [n]
  (if (= n (last (filter #(> n %)) (tri*)))
    true
    false))
