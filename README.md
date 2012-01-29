# clogs

clogs is a tiny tool to merge log files and sort the records according to timestamps.

## Usage

    $ java -jar clogs-0.0.1-SNAPSHOT-standalone.jar [ -h ] <logfile>...

## Example

    $ java -jar clogs-0.0.1-SNAPSHOT-standalone.jar ./log/alert.log ./log/trace.trc ./log/listener.log

## License

Copyright (C) 2012 Takashi Hashizume

Distributed under the Eclipse Public License, the same as Clojure.
