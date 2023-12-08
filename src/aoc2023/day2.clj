(ns aoc2023.day2 
  (:require [aoc2023.helpers :refer [get-input-lines]]))

(def maximums {:red 12
               :green 13
               :blue 14})

(def input-lines (get-input-lines "day2.txt"))

(defn get-game-id [line]
  (Integer/parseInt (re-find #"\d+" line)))

(defn get-highest-cube-counts [line]
  (let [cube-counts (re-seq #"(\d+) (blue|red|green)" line)]
    cube-counts))

(defn get-game-info [line]
  (let [id (get-game-id line)
        maximum-cube-counts (get-highest-cube-counts line)
        convert-count (fn [count]
                        (let [[_ count colour] count]
                          {(keyword colour) (Integer/parseInt count)}))]
    (as-> (map convert-count maximum-cube-counts) count-maps
      (apply merge-with max count-maps)
      (merge {:id id} count-maps))))

(defn is-game-valid? [game]
  (and (<= (:red game) (:red maximums))
       (<= (:blue game) (:blue maximums)) 
       (<= (:green game) (:green maximums))))

(defn get-game-power [game-info]
  (let [green (:green game-info)
        red (:red game-info)
        blue (:blue game-info)]
    (* red green blue)))

(defn part-one []
  (->> input-lines
       (map get-game-info)
       (filter is-game-valid?)
       (map :id)
       (apply +)))

(defn part-two []
  (->> input-lines
       (map get-game-info)
       (map get-game-power)
       (apply +)))

(comment
  (part-one) 
  (part-two) 
  )
