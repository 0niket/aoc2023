(ns aoc2023.day3
  (:require [clojure.string :as cs]))

(comment
  ;; For getting the rows
  (->> "resources/day3input.txt"
       slurp
       (cs/split-lines)
       count)
  )
(def rows 140)


(comment
  ;; For getting the columns
  (->> "resources/day3input.txt"
       slurp
       (cs/split-lines)
       first
       count)
  )
(def cols 140)


(comment
  ;; For getting the symbols
  (->> "resources/day3input.txt"
       slurp
       (re-seq #"[^\d.\n]+")
       set)
  )
(def symbols #{"=" "*" "%" "/" "-" "&" "#" "+" "$" "@"})
