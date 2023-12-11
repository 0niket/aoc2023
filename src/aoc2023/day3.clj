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

(def lines (-> "resources/day3input.txt"
               slurp
               cs/split-lines))

;; sample numbers ds
(def numbers 
  [{:number 123
    :start-x 1
    :end-x 4
    :y 1}])

(defn file-input->lines
  []
  (->> "resources/day3input.txt"
       slurp
       cs/split-lines))

(defn line->ds [line line-number]
  (let [numbers (re-seq #"\d+" line)]
    (map (fn [number]
           (let [s-number (str number)])
           )
         numbers)
    )
  )
