(ns aoc2023.day1
  (:require [aoc2023.helpers :refer [get-input-lines]]
            [clojure.string :as str]))

(def input (get-input-lines "day1.txt"))

(defn convert-calibration-value [value]
  (case value
    "one" 1
    "two" 2
    "three" 3
    "four" 4
    "five" 5
    "six" 6
    "seven" 7
    "eight" 8
    "nine" 9
    "zero" 0
    value))

(defn get-calibration-value [line pattern reverse-pattern]
  (let [first-value (-> (re-find pattern line)
                        convert-calibration-value)
        last-value (-> (re-find reverse-pattern (str/reverse line))
                       str/reverse
                       convert-calibration-value)]
    (Integer/parseInt (str first-value last-value))))

(defn get-calibration-values [input pattern reverse-pattern]
  (map #(get-calibration-value % pattern reverse-pattern) input))

(defn sum-of-calibration-values [calibration-values]
  (reduce + calibration-values))

(defn calculate-answer [pattern reverse-pattern]
  (-> (get-calibration-values input pattern reverse-pattern)
      sum-of-calibration-values))

(defn part-one []
  (calculate-answer #"\d" #"\d"))

(defn part-two []
  (calculate-answer
   #"\d|zero|one|two|three|four|five|six|seven|eight|nine"
   #"\d|orez|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin"))

(comment
  (part-one)
  (part-two))