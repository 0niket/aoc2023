(ns aoc2023.day2
  (:require [clojure.string :as cs]))

(defn game-number
  [s]
  (->> s
       (re-seq #"\d+")
       first
       Integer/parseInt))

(defn subrounds
  [s]
  (as-> s s
    (cs/split s #";")
    (map #(cs/split % #",") s)
    (map (fn [xs]
           (apply
            hash-map
            (mapcat (fn [ys]
                     (reverse (cs/split (cs/trim ys) #" ")))
                   xs)))
         s)))

(defn line->game-ds
  [line]
  (let [game-number-subrounds-split (cs/split line #":")
        game-number (game-number (first game-number-subrounds-split))
        subrounds (subrounds (second game-number-subrounds-split))]
    {:game-id game-number
     :subrounds subrounds}))

(defn possible-subround? 
  [condition subround]
  (and (< (get subround "red") (get condition "red"))
       (< (get subround "green") (get condition "green"))
       (< (get subround "blue") (get condition "blue"))))

(defn possible-game? 
  [condition game]
  (every? #(possible-subround? condition %) (:subrounds game)))

(defn solve
  [condition]
  (->> "resources/day2input.txt"
       slurp
       cs/split-lines
       (map line->game-ds)
       (filter #(possible-game? condition %))
       (map :game-id)
       (reduce +)))