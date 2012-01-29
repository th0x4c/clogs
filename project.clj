(defproject clogs "0.0.1-SNAPSHOT"
  :description "clogs is a tiny tool to merge log files and sort the records according to timestamps."
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/tools.cli "0.2.1"]]
  :dev-dependencies [[lein-autodoc "0.9.0"]]
  :main clogs.core)
