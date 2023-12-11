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
        your-numbers (line->your-numbers line)])
  )
