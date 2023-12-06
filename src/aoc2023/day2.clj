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
                      (let [cube (cs/split (cs/trim ys) #" ")]
                        [(keyword (second cube)) (Integer/parseInt (first cube))]))
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
  (and (<= (get subround :red 0) (:red condition))
       (<= (get subround :green 0) (:green condition))
       (<= (get subround :blue 0) (:blue condition))))

(defn possible-game? 
  [condition game]
  (every? #(possible-subround? condition %) (:subrounds game)))

(defn solve
  [condition]
  (->> "resources/day2input.txt"
       slurp
       cs/split-lines
       (map line->game-ds)
       (filter (fn [game] (possible-game? condition game)))
       (map :game-id)
       (reduce +)))