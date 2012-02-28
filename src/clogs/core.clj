(ns clogs.core
  (:use [clogs.utils])
  (:use [clogs.config])
  (:use [clojure.tools.cli])
  (:use [clojure.java.io :only (writer)])
  (:gen-class))

(defn clogs-partition
  [re-format-vec  str-seq]
  (letfn [(parse-line [line] (date-parse-line re-format-vec line))]
    (partition-by-cat #(if-let [f (first %)] (parse-line f))
                      (partition-with-header-all parse-line str-seq))))

(defn file->clogs-seq
  [re-format-vec filename & opts]
  (let [p (clogs-partition re-format-vec (apply read-lines (cons filename opts)))]
    (map (fn [s] {:timestamp (date-parse-line re-format-vec (str (first s)))
                  :filename filename
                  :log s}) p)))

(defn merge-clogs-seq
  [& clogs-seqs]
  (apply (partial merge-sorted-seq-by :timestamp) clogs-seqs))

(defn add-only-first-str
  [separator s strs]
  (let [x (str s separator)
        y (str (apply str (repeat (count s) " ")) separator)]
    (cons (str x (first strs)) (map #(str y %) (rest strs)))))

(def fname-len 20)

(defn fname-str
  [fname]
  (if (> (count fname) fname-len)
    (last-n-str fname-len fname)
    (str fname (apply str (repeat (- fname-len (count fname)) " ")))))

(defn clogs-seq->strs
  [clgs]
  (letfn [(subf
            [ss]
            (let [ds (if-let [v (:timestamp (first ss))]
                       (date-str "MM-dd HH:mm:ss.SSS" v)
                       "                  ")]
              (->> ss
                   (mapcat #(add-only-first-str "|" (fname-str (:filename %)) (:log %)))
                   (add-only-first-str "|" ds)
                   (map #(str % "\n")))))]
    (mapcat #(subf %) (partition-by :timestamp clgs))))

(defn file->style
  [fname]
  (first (filter #(re-find (:filename-re %) (basename fname)) clogs-styles)))

(defn file-seq->clogs-strs
  [files & opts]
  (->> files
       (map #(apply file->clogs-seq (concat [(:date-format (file->style %)) %] opts)))
       (apply merge-clogs-seq)
       clogs-seq->strs))

(defn -main [& args]
  (let [[options args banner] (cli args
                                   ["-h" "--help" "Show help" :default false :flag true])]
    (when (or (:help options) (empty? args))
      (println "Description:")
      (println)
      (println " clogs is a tiny tool to merge log files and sort the records according to timestamps.")
      (println)
      (println banner)
      (println "Example:")
      (println)
      (println " $ java -jar clogs-0.0.1-SNAPSHOT-standalone.jar ./log/alert.log ./log/trace.trc ./file/listener.log")
      (println)
      (System/exit 0))
    (when-let [no-files (not-empty (filter (complement file-exists?) args))]
      (println "No such file or directory.")
      (println (apply str (interpose "\n" no-files)))
      (System/exit 1))
    (with-open [w (writer System/out :encoding clogs-writer-encoding)]
      (doseq [s (file-seq->clogs-strs args :encoding clogs-reader-encoding)]
        (.write w s)))))
