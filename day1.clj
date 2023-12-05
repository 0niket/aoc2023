(ns aoc2023.day1
  (:require [clojure.string :as cs]))

(defn part-one 
  []
  (->> "resources/day1input.txt"
       slurp
       cs/split-lines
       (map (fn [line]
              (let [digits (re-seq #"\d" line)
                    first-digit (first digits)
                    last-digit (last digits)]
                (Integer/parseInt (str first-digit last-digit)))))
       (reduce +)))

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

(defn- line-to-two-digit-num
  [line]
  (let [lc-line (cs/lower-case line)
        digits (re-seq #"\d|one|two|three|four|five|six|seven|eight|nine" lc-line)
        first-digit (word-to-int (first digits))
        last-digit (word-to-int (last digits))]
    (Integer/parseInt (str first-digit last-digit))))

(defn part-two 
  []
  (->> "resources/day1input.txt"
       slurp
       cs/split-lines
       (map line-to-two-digit-num)
       (reduce + 0)))