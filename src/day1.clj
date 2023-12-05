(ns aoc2023.day1
  (:require [clojure.string :as cs]))

(defn line-to-two-digit-num-part-1
  [line]
  (let [digits (re-seq #"\d" line)
        first-digit (first digits)
        last-digit (last digits)]
    (Integer/parseInt (str first-digit last-digit))))

(defn word-to-int
  [s]
  (case s
    "one" 1
    "two" 2
    "three" 3
    "four" 4
    "five" 5
    "six" 6
    "seven" 7
    "eight" 8
    "nine" 9
    s))

(let [first-digit-re #"\d|one|two|three|four|five|six|seven|eight|nine"
      last-digit-re #".*(\d|one|two|three|four|five|six|seven|eight|nine).*"]
 (defn line-to-two-digit-num-part-2
  [line]
  (let [lc-line (cs/lower-case line)
        first-digit (word-to-int (re-find first-digit-re lc-line))
        last-digit (word-to-int (second (re-find last-digit-re lc-line)))]
    (Integer/parseInt (str first-digit last-digit)))))

(defn solve
  []
  (->> "resources/day1input.txt"
       slurp
       cs/split-lines
       ;; Replace function passed with part1 or part2 functions
       (map line-to-two-digit-num-part-2)
       (reduce + 0)))
