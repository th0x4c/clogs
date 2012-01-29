(ns clogs.test.core
  (:use [clogs.core])
  (:use [clojure.test])
  (:use [clogs.test.helper])
  (:use [clogs.config]))

(deftest test-clogs-partition
  (is (= (clogs-partition alert-date-format alert-log-seq) alert-log-str-partitions))
  (is (= (clogs-partition trace-date-format trace-log-seq) trace-log-str-partitions))
  (is (= (clogs-partition listener-date-format listener-log-seq) listener-log-str-partitions)))

(deftest test-file->clogs-seq
  (is (= (file->clogs-seq alert-date-format "test/clogs/test/file/alert.log") alert-clogs-seq))
  (is (= (file->clogs-seq trace-date-format "test/clogs/test/file/trace.trc") trace-clogs-seq))
  (is (= (file->clogs-seq listener-date-format "test/clogs/test/file/listener.log") listener-clogs-seq)))

;; (deftest test-merge-clogs-seq ;; Not implemented
;;   (is (= (merge-clogs-seq alert-clogs-seq trace-clogs-seq listener-clogs-seq) "")))

(deftest test-add-only-first-str
  (is (= (add-only-first-str "|" "12:34" ["aaa" "bbb" "ccc"]) ["12:34|aaa" "     |bbb" "     |ccc"])))

;; (deftest test-clogs-strs ;; Not implemented
;;   (is (= (clogs-strs (merge-clogs-seq alert-clogs-seq trace-clogs-seq listener-clogs-seq)) "")))

(deftest test-file->style
  (is (= (file->style "test/clogs/test/file/trace.trc") style-trace))
  (is (= (file->style "test/clogs/test/file/not_exist.file") style-default)))
