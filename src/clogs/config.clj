(ns clogs.config)

(def style-alert
  {:name        "Alert log"
   :filename-re #"alert.*\.log"
   :date-format [[#"(Sun|Mon|Tue|Wed|Thu|Fri|Sat) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([12][09][0-9][0-9])" "EEE MMM dd HH:mm:ss yyyy"]]})

(def style-trace
  {:name        "Trace log"
   :filename-re #".*\.trc"
   :date-format [[#"([12][09][0-9][0-9])-([01][0-9])-([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])\.([0-9][0-9][0-9])" "yyyy-MM-dd HH:mm:ss.SSS"]]})

(def style-listener
  {:name        "Listener log"
   :filename-re #"listener.*\.log"
   :date-format [[#"([0-3][0-9])-([1-9])月 -([12][09][0-9][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])" "dd-M月 -yyyy HH:mm:ss"]
                 [#"([0-3][0-9])-(1[012])月-([12][09][0-9][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])" "dd-M月-yyyy HH:mm:ss"]
                 ((:date-format style-alert) 0)]})

(def style-default
  {:name        "Default style"
   :filename-re #".*"
   :date-format [[#"(Sun|Mon|Tue|Wed|Thu|Fri|Sat) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([A-Z][A-Z][A-Z]) ([12][09][0-9][0-9])" "EEE MMM dd HH:mm:ss z yyyy"]
                 ((:date-format style-alert) 0)
                 ((:date-format style-trace) 0)]})

(def clogs-styles [style-alert style-trace style-listener style-default])
(def clogs-writer-encoding "UTF-8")
;; (def clogs-reader-encoding "Shift_JIS")
(def clogs-reader-encoding "UTF-8")
