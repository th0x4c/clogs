(ns clogs.test.utils
  (:use [clogs.utils])
  (:use [clojure.test])
  (:use [clogs.test.helper]))

;; String
(deftest test-num-of-left-whitespace
  (is (= (num-of-left-whitespace "  abc") 2))
  (is (= (num-of-left-whitespace "abc") 0)))

(deftest test-re-match-str
  (is (= (re-match-str #"\d\d\d\d" "abc123456def") "1234")
      (= (re-match-str #"\d(\d\d)\d" "abc123456def") "1234")))

(deftest test-basename
  (is (= (basename "test/clogs/test/file/alert.log") "alert.log"))
  (is (= (basename "test/clogs/test/file/") "file")))

;; Date
(deftest test-date-parse
  (is (= (date-parse "EEE MMM dd HH:mm:ss z yyyy" "Thu Jan 01 00:00:00 UTC 1970")
         0))
  (is (= (date-parse "EEE MMM dd HH:mm:ss z yyyy" "Sat Feb 14 08:31:30 JST 2009")
         1234567890000))
  (is (= (date-parse "yyyy-MM-dd HH:mm:ss.SSS Z" "2009-02-14 08:31:30.123 +0900")
         1234567890123)))

(deftest test-date-parse-line
  (is (= (date-parse-line #"([12][09][0-9][0-9])-([01][0-9])-([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])\.([0-9][0-9][0-9]) \+(\d\d\d\d)" "yyyy-MM-dd HH:mm:ss.SSS Z" "*** 2009-02-14 08:31:30.123 +0900 ***")
         1234567890123))
  (is (= (date-parse-line #"[12][09][0-9][0-9]-[01][0-9]-[0-3][0-9] [0-2][0-9]:[0-6][0-9]:[0-6][0-9]\.[0-9][0-9][0-9] \+\d\d\d\d" "yyyy-MM-dd HH:mm:ss.SSS Z" "*** 2009-02-14 08:31:30.123 +0900 ***")
         1234567890123))
  (is (nil? (date-parse-line #"[12][09][0-9][0-9]-[01][0-9]-[0-3][0-9] [0-2][0-9]:[0-6][0-9]:[0-6][0-9]\.[0-9][0-9][0-9] \+\d\d\d\d" "yyyy-MM-dd HH:mm:ss.SSS Z" "*** don't match string ***")))
  (is (= (date-parse-line [[#"[12][09][0-9][0-9]-[01][0-9]-[0-3][0-9] [0-2][0-9]:[0-6][0-9]:[0-6][0-9]\.[0-9][0-9][0-9] \+\d\d\d\d" "yyyy-MM-dd HH:mm:ss.SSS Z"]] "*** 2009-02-14 08:31:30.123 +0900 ***")
         1234567890123))
  (is (= (date-parse-line
          [[#"([0-3][0-9])-([1-9])月 -([12][09][0-9][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([A-Z][A-Z][A-Z])" "dd-M月 -yyyy HH:mm:ss z"]
           [#"([0-3][0-9])-(1[012])月-([12][09][0-9][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([A-Z][A-Z][A-Z])" "dd-M月-yyyy HH:mm:ss z"]
           [#"(Sun|Mon|Tue|Wed|Thu|Fri|Sat) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([A-Z][A-Z][A-Z]) ([12][09][0-9][0-9])"
            "EEE MMM dd HH:mm:ss z yyyy"]]
          "14-2月 -2009 08:31:30 JST * service_update * xe * 0")
         1234567890000)))

(deftest test-date-str
  (is (= (date-str "yyyy-MM-dd HH:mm:ss.SSS z" 1234567890123) "2009-02-14 08:31:30.123 JST")))

;; Sequence
(deftest test-delta-seq
  (is (= (delta-seq [2 3 4 5]) [2 1 1 1]))
  (is (= (delta-seq '(3 2 6 4 5)) '(3 -1 4 -2 1))))

(deftest test-positions
  (is (= (positions odd? [1 2 3 4 5]) [0 2 4]))
  (is (= (positions odd? [1 2 4 6 3 8 10 5]) [0 4 7]))
  (is (= (positions even? [1 2 4 6 3 8 10 5]) [1 2 3 5 6])))

(deftest test-partition-by-coll
  (is (= (partition-by-coll [2 1] [:a :a :a :a :a :a])
         '((:a :a) (:a) (:a :a :a))))
  (is (= (partition-by-coll [2 1 3] [:a :a :a :a :a :a])
         '((:a :a) (:a) (:a :a :a) ()))))

(deftest test-partition-with-header-all
  (is (= (partition-with-header-all odd? [1 2 4 3 6 5 8 10])
         '(() (1 2 4) (3 6) (5 8 10))))
  (is (= (partition-with-header-all even? [1 2 4 3 6 5 8 10])
         '((1) (2) (4 3) (6 5) (8) (10)))))

(deftest test-partition-with-header
  (is (= (partition-with-header odd? [1 2 4 3 6 5 8 10])
         '((1 2 4) (3 6) (5 8 10))))
  (is (= (partition-with-header even? [1 2 4 3 6 5 8 10])
         '((2) (4 3) (6 5) (8) (10)))))

(deftest test-partition-by-cat
  (is (= (partition-by-cat first '((:a [0 1]) (:a 2) (:a [0 3]) (:b 3) (:c 4) (:c 5)))
         '((:a [0 1] :a 2 :a [0 3]) (:b 3) (:c 4 :c 5)))))

(deftest test-merge-sorted-seq
  (is (= (merge-sorted-seq [1 3 4 5 7 9] [2 3 5 6 8]) [1 2 3 3 4 5 5 6 7 8 9]))
  (is (= (merge-sorted-seq [1 5 6] [nil 3 4 8] [0 2 3 5 7 9]) [nil 0 1 2 3 3 4 5 5 6 7 8 9])))

(deftest test-merge-sorted-seq-by
  (is (= (merge-sorted-seq-by second [[:a 1] [:a 3] [:a 4]] [[:b 2] [:b 3] [:b 5]]) [[:a 1] [:b 2] [:a 3] [:b 3] [:a 4] [:b 5]]))
  (is (= (merge-sorted-seq-by second [[:a 1] [:a 3] [:a 4]] [[:b 2] [:b 3] [:b 5]] [[:c 1] [:c 3] [:c 6]]) [[:a 1] [:c 1] [:b 2] [:a 3] [:b 3] [:c 3] [:a 4] [:b 5] [:c 6]])))

;; I/O
(deftest test-read-lines
  (is (= (read-lines "test/clogs/test/file/alert.log") alert-log-seq)))
