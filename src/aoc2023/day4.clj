(ns aoc2023.day4
  (:require [clojure.string :as cs]))

(defn input-file->lines
  []
  (->> "resources/day4input.txt"
       slurp
       cs/split-lines))

(defn line->winning-numbers
  [line]
  (let [colon-index (cs/last-index-of line ":")
        bar-index (cs/last-index-of line "|")
        s-numbers (-> line
                      (subs (inc colon-index) bar-index)
                      cs/trim
                      (cs/split #" "))]
    (->> s-numbers
         (map #(when (seq %)
                 (Integer/parseInt %)))
         (filter (complement nil?)))))

(defn line->your-numbers 
  [line]
  (let [bar-index (cs/last-index-of line "|")
        s-numbers (-> line
                      (subs (inc bar-index))
                      cs/trim
                      (cs/split #" "))]
    (->> s-numbers 
         (map #(when (seq %)
                 (Integer/parseInt %)))
         (filter (complement nil?)))))

(defn line->matching-numbers
  [line]
  (let [winning-numbers (line->winning-numbers line)
        your-numbers (line->your-numbers line)]
    (filter (fn [n] (some #(= % n) winning-numbers))
            your-numbers)))

(defn line->points
  [line]
  (let [matching-numbers (line->matching-numbers line)
        matching-numbers-count (count matching-numbers)]
    (cond
      (> matching-numbers-count 1) (->> matching-numbers
                                        rest
                                        (map (fn [_] 2))
                                        (reduce *))
      (= matching-numbers-count 1) 1
      :else 0)))

(defn solve-part-1
  []
  (->> (input-file->lines)
       (map line->points)
       (reduce +)))

(defn solve-part-2
  []
  (let [lines (input-file->lines)
        card-instances (-> lines
                           count
                           (repeat 0)
                           vec)
        matching-numbers-count (map (comp count line->matching-numbers) lines)
        ]
    matching-numbers-count))

;; If first 5 numbers are [7 10 0 10 5]
;; then the sum would be 12 and it will be calculated in following way:
;; => [1 1 1 1 1]
;; => [1 2 2 2 2]
;; => [1 2 2 2 2]
;; => [1 2 2 3 3]
;; => [1 2 2 3 4]
;; Possible approch would be to use reduce on card-instances and matching 
;; numbers count.
;; Second approach would be to do it in 2 phases.
;; First is to process and spread the count across the card-instances
;; Second is to reduce it with +.

