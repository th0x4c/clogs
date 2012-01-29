(ns clogs.utils
  (:use [clojure.java.io :only (reader)])
  (:import (java.io BufferedReader File)
           (java.text SimpleDateFormat)
           (java.util Date Locale)))

;; String
(defn num-of-left-whitespace
  "Returns a number of whitespace on the left-hand side of the string."
  [s]
  (loop [i 0]
    (if (Character/isWhitespace (.charAt s i))
      (recur (inc i))
      i)))

(defn re-match-str
  [re s]
  (let [m (re-find re s)] (if (vector? m) (first m) m)))

(defn last-n-str
  [n s]
  (apply str (take-last n s)))

(defn basename
  [file-name]
  (.getName (new File file-name)))

;; Date
(defn date-parse
  "Parses the given date/time string using the given date format string and
  returns the number of milliseconds since the Unix epoch."
  [format-str date-str]
  (.getTime (.parse (new SimpleDateFormat format-str Locale/US) date-str)))

(defn date-parse-line
  "Parses line-str matching the given date/time regex string date-re using
  the given date format string format-str and returns the number of milliseconds
  since the Unix epoch. Returns nil if line-str doesn't match date-re."
  ([date-re format-str line-str]
     (if-let [rms (re-match-str date-re line-str)] (date-parse format-str rms) nil))
  ([re-format-vec line-str]
     (some identity (map (fn [[re ft]] (date-parse-line re ft line-str))
                         re-format-vec))))

(defn date-str
  [format-str msec]
  (.format (new SimpleDateFormat format-str Locale/US) (new Date msec)))

;; Sequence
(defn delta-seq
  "Returns a lazy sequence containing difference from previous value."
  [coll]
  (map #(- %1 %2) coll (cons 0 coll)))

(defn positions
  "Returns a lazy sequence containing the positions at which pred is true for
  items in coll."
  [pred coll]
  (filter number? (map-indexed (fn [i x] (if (pred x) i)) coll)))

(defn partition-by-coll
  [c1 c2]
  (if-let [n (first c1)]
    (lazy-seq (cons (take n c2) (partition-by-coll (rest c1) (drop n c2))))
    (list c2)))

(defn partition-with-header-all
  [pred coll]
  (partition-by-coll (delta-seq (positions pred coll)) coll))

(defn partition-with-header
  [pred coll]
  (rest (partition-with-header-all pred coll)))

(defn partition-by-cat
  [f coll]
  (map #(reduce into [] %) (partition-by f coll)))

(defn merge-sorted-seq-with-comp
  ([comp coll]
     coll)
  ([comp c1 c2 & colls]
     (lazy-seq
      (let [minv (first (sort comp (map first (conj colls c2 c1))))
            split-seq  (map #(split-with (fn [x] (= minv x)) %) (conj colls c2 c1))]
        (concat (mapcat #(first %) split-seq)
                (apply merge-sorted-seq-with-comp comp (filter #(not-empty %) (mapcat #(rest %) split-seq))))))))

(defn merge-sorted-seq
  [c1 c2 & colls]
  (if colls
    (apply (partial merge-sorted-seq-with-comp compare c1 c2) colls)
    (merge-sorted-seq-with-comp compare c1 c2)))

(defn merge-sorted-seq-by
  [keyfn & colls]
  (apply (partial merge-sorted-seq-with-comp #(compare (keyfn %1) (keyfn %2))) colls))

;; I/O
(defn file-exists?
  [file-name]
  (.exists (new File file-name)))

;; From clojure.contrib
;; http://clojure.github.com/clojure-contrib/duck-streams-api.html#clojure.contrib.duck-streams/read-lines
;; Copyright (c) Stuart Sierra, 2009. All rights reserved.
;; Eclipse Public License 1.0
;; (defn read-lines
;;   "Like clojure.core/line-seq but opens f with reader.  Automatically
;;   closes the reader AFTER YOU CONSUME THE ENTIRE SEQUENCE."
;;   [f]
;;   (let [read-line (fn this [^BufferedReader rdr]
;;                     (lazy-seq
;;                      (if-let [line (.readLine rdr)]
;;                        (cons line (this rdr))
;;                        (.close rdr))))]
;;     (read-line (reader f))))

(defn read-lines
  "Like clojure.core/line-seq but opens f with reader.  Automatically
  closes the reader AFTER YOU CONSUME THE ENTIRE SEQUENCE.

  Pulled and changed from clojure.contrib.duck-streams."
  [f & opts]
  (let [read-line (fn this [#^BufferedReader rdr]
                    (lazy-seq
                     (if-let [line (.readLine rdr)]
                       (cons line (this rdr))
                       (.close rdr))))]
    (read-line (apply reader (cons f opts)))))
