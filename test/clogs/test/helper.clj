(ns clogs.test.helper
  (:import (java.io BufferedReader StringReader)))

(def alert-log-str
  "Sat Jan 14 02:38:13 2012
Starting ORACLE instance (normal)
Sat Jan 14 02:38:38 2012
LICENSE_MAX_SESSION = 0
LICENSE_SESSIONS_WARNING = 0
Picked latch-free SCN scheme 2
Using LOG_ARCHIVE_DEST_1 parameter default value as USE_DB_RECOVERY_FILE_DEST
Autotune of undo retention is turned on. 
IMODE=BR
ILAT =18
Sat Jan 14 02:38:50 2012
LICENSE_MAX_USERS = 0
SYS auditing is disabled
Starting up:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production.
Using parameter settings in server-side pfile C:\\ORACLEXE\\APP\\ORACLE\\PRODUCT\\11.2.0\\SERVER\\DATABASE\\INITXE.ORA
System parameters with non-default values:
  sessions                 = 172
  spfile                   = \"C:\\ORACLEXE\\APP\\ORACLE\\PRODUCT\\11.2.0\\SERVER\\DBS\\SPFILEXE.ORA\"
  memory_target            = 240M
  control_files            = \"C:\\ORACLEXE\\APP\\ORACLE\\ORADATA\\XE\\CONTROL.DBF\"
  compatible               = \"11.2.0.0.0\"
  db_recovery_file_dest    = \"C:\\oraclexe\\app\\oracle\\fast_recovery_area\"
  db_recovery_file_dest_size= 10G
  undo_management          = \"AUTO\"
  undo_tablespace          = \"UNDOTBS1\"
  remote_login_passwordfile= \"EXCLUSIVE\"
  dispatchers              = \"(PROTOCOL=TCP) (SERVICE=XEXDB)\"
  shared_servers           = 4
  job_queue_processes      = 4
  audit_file_dest          = \"C:\\ORACLEXE\\APP\\ORACLE\\ADMIN\\XE\\ADUMP\"
  db_name                  = \"XE\"
  open_cursors             = 300
  diagnostic_dest          = \"C:\\ORACLEXE\\APP\\ORACLE\"
Sat Jan 14 02:39:08 2012
PMON started with pid=2, OS id=3892 
Sat Jan 14 02:39:08 2012
PSP0 started with pid=3, OS id=3896 
Sat Jan 14 02:39:09 2012
VKTM started with pid=4, OS id=4072 at elevated priority
VKTM running at (10)millisec precision with DBRM quantum (100)ms
Sat Jan 14 02:39:09 2012
GEN0 started with pid=5, OS id=4076 
Sat Jan 14 02:39:09 2012
DIAG started with pid=6, OS id=4080 
Sat Jan 14 02:39:09 2012
DBRM started with pid=7, OS id=4084 
Sat Jan 14 02:39:09 2012
DIA0 started with pid=8, OS id=4088 
Sat Jan 14 02:39:09 2012
MMAN started with pid=9, OS id=4092 
Sat Jan 14 02:39:09 2012
DBW0 started with pid=10, OS id=268 
Sat Jan 14 02:39:09 2012
LGWR started with pid=11, OS id=264 
Sat Jan 14 02:39:09 2012
CKPT started with pid=12, OS id=244 
Sat Jan 14 02:39:10 2012
SMON started with pid=13, OS id=172 
Sat Jan 14 02:39:10 2012
RECO started with pid=14, OS id=252 
Sat Jan 14 02:39:10 2012
MMON started with pid=15, OS id=272 
Sat Jan 14 02:39:10 2012
MMNL started with pid=16, OS id=284 
Sat Jan 14 02:39:10 2012
starting up 1 dispatcher(s) for network address '(ADDRESS=(PARTIAL=YES)(PROTOCOL=TCP))'...
starting up 4 shared server(s) ...
ORACLE_BASE from environment = C:\\oraclexe\\app\\oracle
Sat Jan 14 02:39:22 2012
alter database mount exclusive
Sat Jan 14 02:39:28 2012
Successful mount of redo thread 1, with mount id 2653644186
Database mounted in Exclusive Mode
Lost write protection disabled
Sat Jan 14 02:39:40 2012
Completed: alter database mount exclusive
alter database open")

(def alert-log-seq (line-seq (BufferedReader. (StringReader. alert-log-str))))

(def trace-log-str
  "Trace file C:\\oraclexe\\app\\oracle\\diag\\rdbms\\xe\\xe\\trace\\xe_ora_780.trc
Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production
Windows XP Version V5.1 Service Pack 3
CPU                 : 1 - type 586, 1 Physical Cores
Process Affinity    : 0x0x00000000
Memory (Avail/Total): Ph:362M/767M, Ph+PgF:988M/1490M, VA:1593M/2047M
VM name             : VMWare Version (6)
Instance name: xe
Redo thread mounted by this instance: 0 <none>
Oracle process number: 0
Windows thread id: 780, image: ORACLE.EXE (SHAD)


*** 2012-01-14 02:38:48.187
kcrfwy: minimum sleep is 26801 usecs (overhead is 0 usecs)
Running with 1 strand for Non-Enterprise Edition
Running without dynamic strands for Non-Enterprise Edition

*** 2012-01-14 02:39:08.015
*** SESSION ID:(1.1) 2012-01-14 02:39:08.015
*** SERVICE NAME:() 2012-01-14 02:39:08.015

DBRM(kskinitrm): cpu_count = 1")

(def trace-log-seq (line-seq (BufferedReader. (StringReader. trace-log-str))))

(def listener-log-str
  "Sat Jan 14 02:37:23 2012
System parameter file is C:\\oraclexe\\app\\oracle\\product\\11.2.0\\server\\network\\admin\\listener.ora
Log messages written to C:\\oraclexe\\app\\oracle\\diag\\tnslsnr\\browning\\listener\\alert\\log.xml
Trace information written to C:\\oraclexe\\app\\oracle\\diag\\tnslsnr\\browning\\listener\\trace\\ora_500_584.trc
Trace level is currently 0

pid=500で起動しました
リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(PIPENAME=\\\\.\\pipe\\EXTPROC1ipc)))
リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=1521)))
Listener completed notification to CRS on start

TIMESTAMP * CONNECT DATA [* PROTOCOL INFO] * EVENT [* SID] * RETURN CODE
Sat Jan 14 02:39:08 2012
Dynamic address is already listened on (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=1521)))
14-1月 -2012 02:39:08 * service_register * xe * 0
14-1月 -2012 02:39:11 * service_update * xe * 0
Sat Jan 14 02:39:28 2012
14-1月 -2012 02:39:28 * service_update * xe * 0
Sat Jan 14 02:40:10 2012
14-1月 -2012 02:40:10 * service_update * xe * 0
14-1月 -2012 02:40:19 * service_update * xe * 0
Sat Jan 14 02:40:22 2012
リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=8080))(Presentation=HTTP)(Session=RAW))
14-1月 -2012 02:40:22 * service_update * xe * 0")

(def listener-log-seq (line-seq (BufferedReader. (StringReader. listener-log-str))))

(def alert-log-str-partitions
  '(()
    ("Sat Jan 14 02:38:13 2012"
     "Starting ORACLE instance (normal)")
    ("Sat Jan 14 02:38:38 2012"
     "LICENSE_MAX_SESSION = 0"
     "LICENSE_SESSIONS_WARNING = 0"
     "Picked latch-free SCN scheme 2"
     "Using LOG_ARCHIVE_DEST_1 parameter default value as USE_DB_RECOVERY_FILE_DEST"
     "Autotune of undo retention is turned on. "
     "IMODE=BR"
     "ILAT =18")
    ("Sat Jan 14 02:38:50 2012"
     "LICENSE_MAX_USERS = 0"
     "SYS auditing is disabled"
     "Starting up:"
     "Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production."
     "Using parameter settings in server-side pfile C:\\ORACLEXE\\APP\\ORACLE\\PRODUCT\\11.2.0\\SERVER\\DATABASE\\INITXE.ORA"
     "System parameters with non-default values:"
     "  sessions                 = 172"
     "  spfile                   = \"C:\\ORACLEXE\\APP\\ORACLE\\PRODUCT\\11.2.0\\SERVER\\DBS\\SPFILEXE.ORA\""
     "  memory_target            = 240M"
     "  control_files            = \"C:\\ORACLEXE\\APP\\ORACLE\\ORADATA\\XE\\CONTROL.DBF\""
     "  compatible               = \"11.2.0.0.0\""
     "  db_recovery_file_dest    = \"C:\\oraclexe\\app\\oracle\\fast_recovery_area\""
     "  db_recovery_file_dest_size= 10G"
     "  undo_management          = \"AUTO\""
     "  undo_tablespace          = \"UNDOTBS1\""
     "  remote_login_passwordfile= \"EXCLUSIVE\""
     "  dispatchers              = \"(PROTOCOL=TCP) (SERVICE=XEXDB)\""
     "  shared_servers           = 4"
     "  job_queue_processes      = 4"
     "  audit_file_dest          = \"C:\\ORACLEXE\\APP\\ORACLE\\ADMIN\\XE\\ADUMP\""
     "  db_name                  = \"XE\""
     "  open_cursors             = 300"
     "  diagnostic_dest          = \"C:\\ORACLEXE\\APP\\ORACLE\"")
    ("Sat Jan 14 02:39:08 2012"
     "PMON started with pid=2, OS id=3892 "
     "Sat Jan 14 02:39:08 2012"
     "PSP0 started with pid=3, OS id=3896 ")
    ("Sat Jan 14 02:39:09 2012"
     "VKTM started with pid=4, OS id=4072 at elevated priority"
     "VKTM running at (10)millisec precision with DBRM quantum (100)ms"
     "Sat Jan 14 02:39:09 2012"
     "GEN0 started with pid=5, OS id=4076 "
     "Sat Jan 14 02:39:09 2012"
     "DIAG started with pid=6, OS id=4080 "
     "Sat Jan 14 02:39:09 2012"
     "DBRM started with pid=7, OS id=4084 "
     "Sat Jan 14 02:39:09 2012"
     "DIA0 started with pid=8, OS id=4088 "
     "Sat Jan 14 02:39:09 2012"
     "MMAN started with pid=9, OS id=4092 "
     "Sat Jan 14 02:39:09 2012"
     "DBW0 started with pid=10, OS id=268 "
     "Sat Jan 14 02:39:09 2012"
     "LGWR started with pid=11, OS id=264 "
     "Sat Jan 14 02:39:09 2012"
     "CKPT started with pid=12, OS id=244 ")
    ("Sat Jan 14 02:39:10 2012"
     "SMON started with pid=13, OS id=172 "
     "Sat Jan 14 02:39:10 2012"
     "RECO started with pid=14, OS id=252 "
     "Sat Jan 14 02:39:10 2012"
     "MMON started with pid=15, OS id=272 "
     "Sat Jan 14 02:39:10 2012"
     "MMNL started with pid=16, OS id=284 "
     "Sat Jan 14 02:39:10 2012"
     "starting up 1 dispatcher(s) for network address '(ADDRESS=(PARTIAL=YES)(PROTOCOL=TCP))'..."
     "starting up 4 shared server(s) ..."
     "ORACLE_BASE from environment = C:\\oraclexe\\app\\oracle")
    ("Sat Jan 14 02:39:22 2012"
     "alter database mount exclusive")
    ("Sat Jan 14 02:39:28 2012"
     "Successful mount of redo thread 1, with mount id 2653644186"
     "Database mounted in Exclusive Mode"
     "Lost write protection disabled")
    ("Sat Jan 14 02:39:40 2012"
     "Completed: alter database mount exclusive"
     "alter database open")))

(def trace-log-str-partitions
  '(("Trace file C:\\oraclexe\\app\\oracle\\diag\\rdbms\\xe\\xe\\trace\\xe_ora_780.trc"
     "Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production"
     "Windows XP Version V5.1 Service Pack 3"
     "CPU                 : 1 - type 586, 1 Physical Cores"
     "Process Affinity    : 0x0x00000000"
     "Memory (Avail/Total): Ph:362M/767M, Ph+PgF:988M/1490M, VA:1593M/2047M"
     "VM name             : VMWare Version (6)"
     "Instance name: xe"
     "Redo thread mounted by this instance: 0 <none>"
     "Oracle process number: 0"
     "Windows thread id: 780, image: ORACLE.EXE (SHAD)"
     ""
     "")
    ("*** 2012-01-14 02:38:48.187"
     "kcrfwy: minimum sleep is 26801 usecs (overhead is 0 usecs)"
     "Running with 1 strand for Non-Enterprise Edition"
     "Running without dynamic strands for Non-Enterprise Edition"
     "")
    ("*** 2012-01-14 02:39:08.015"
     "*** SESSION ID:(1.1) 2012-01-14 02:39:08.015"
     "*** SERVICE NAME:() 2012-01-14 02:39:08.015"
     ""
     "DBRM(kskinitrm): cpu_count = 1")))

(def listener-log-str-partitions
  '(()
    ("Sat Jan 14 02:37:23 2012"
     "System parameter file is C:\\oraclexe\\app\\oracle\\product\\11.2.0\\server\\network\\admin\\listener.ora"
     "Log messages written to C:\\oraclexe\\app\\oracle\\diag\\tnslsnr\\browning\\listener\\alert\\log.xml"
     "Trace information written to C:\\oraclexe\\app\\oracle\\diag\\tnslsnr\\browning\\listener\\trace\\ora_500_584.trc"
     "Trace level is currently 0"
     ""
     "pid=500で起動しました"
     "リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(PIPENAME=\\\\.\\pipe\\EXTPROC1ipc)))"
     "リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=1521)))"
     "Listener completed notification to CRS on start"
     ""
     "TIMESTAMP * CONNECT DATA [* PROTOCOL INFO] * EVENT [* SID] * RETURN CODE")
    ("Sat Jan 14 02:39:08 2012"
     "Dynamic address is already listened on (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=1521)))"
     "14-1月 -2012 02:39:08 * service_register * xe * 0")
    ("14-1月 -2012 02:39:11 * service_update * xe * 0")
    ("Sat Jan 14 02:39:28 2012"
     "14-1月 -2012 02:39:28 * service_update * xe * 0")
    ("Sat Jan 14 02:40:10 2012"
     "14-1月 -2012 02:40:10 * service_update * xe * 0")
    ("14-1月 -2012 02:40:19 * service_update * xe * 0")
    ("Sat Jan 14 02:40:22 2012"
     "リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=8080))(Presentation=HTTP)(Session=RAW))"
     "14-1月 -2012 02:40:22 * service_update * xe * 0")))

(def alert-date-format
  [[#"(Sun|Mon|Tue|Wed|Thu|Fri|Sat) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([12][09][0-9][0-9])" "EEE MMM dd HH:mm:ss yyyy"]])

(def trace-date-format
  [[#"([12][09][0-9][0-9])-([01][0-9])-([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])\.([0-9][0-9][0-9])" "yyyy-MM-dd HH:mm:ss.SSS"]])

(def listener-date-format
  [[#"([0-3][0-9])-([1-9])月 -([12][09][0-9][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])" "dd-M月 -yyyy HH:mm:ss"]
   [#"([0-3][0-9])-(1[012])月-([12][09][0-9][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9])" "dd-M月-yyyy HH:mm:ss"]
   [#"(Sun|Mon|Tue|Wed|Thu|Fri|Sat) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) ([0-3][0-9]) ([0-2][0-9]:[0-6][0-9]:[0-6][0-9]) ([12][09][0-9][0-9])" "EEE MMM dd HH:mm:ss yyyy"]])

(def alert-clogs-seq
  [[nil [["test/clogs/test/file/alert.log"
          []]]]
   [1326476293000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:38:13 2012"
                     "Starting ORACLE instance (normal)"]]]]
   [1326476318000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:38:38 2012"
                     "LICENSE_MAX_SESSION = 0"
                     "LICENSE_SESSIONS_WARNING = 0"
                     "Picked latch-free SCN scheme 2"
                     "Using LOG_ARCHIVE_DEST_1 parameter default value as USE_DB_RECOVERY_FILE_DEST"
                     "Autotune of undo retention is turned on. "
                     "IMODE=BR"
                     "ILAT =18"]]]]
   [1326476330000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:38:50 2012"
                     "LICENSE_MAX_USERS = 0"
                     "SYS auditing is disabled"
                     "Starting up:"
                     "Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production."
                     "Using parameter settings in server-side pfile C:\\ORACLEXE\\APP\\ORACLE\\PRODUCT\\11.2.0\\SERVER\\DATABASE\\INITXE.ORA"
                     "System parameters with non-default values:"
                     "  sessions                 = 172"
                     "  spfile                   = \"C:\\ORACLEXE\\APP\\ORACLE\\PRODUCT\\11.2.0\\SERVER\\DBS\\SPFILEXE.ORA\""
                     "  memory_target            = 240M"
                     "  control_files            = \"C:\\ORACLEXE\\APP\\ORACLE\\ORADATA\\XE\\CONTROL.DBF\""
                     "  compatible               = \"11.2.0.0.0\""
                     "  db_recovery_file_dest    = \"C:\\oraclexe\\app\\oracle\\fast_recovery_area\""
                     "  db_recovery_file_dest_size= 10G"
                     "  undo_management          = \"AUTO\""
                     "  undo_tablespace          = \"UNDOTBS1\""
                     "  remote_login_passwordfile= \"EXCLUSIVE\""
                     "  dispatchers              = \"(PROTOCOL=TCP) (SERVICE=XEXDB)\""
                     "  shared_servers           = 4"
                     "  job_queue_processes      = 4"
                     "  audit_file_dest          = \"C:\\ORACLEXE\\APP\\ORACLE\\ADMIN\\XE\\ADUMP\""
                     "  db_name                  = \"XE\""
                     "  open_cursors             = 300"
                     "  diagnostic_dest          = \"C:\\ORACLEXE\\APP\\ORACLE\""]]]]
   [1326476348000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:39:08 2012"
                     "PMON started with pid=2, OS id=3892 "
                     "Sat Jan 14 02:39:08 2012"
                     "PSP0 started with pid=3, OS id=3896 "]]]]
   [1326476349000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:39:09 2012"
                     "VKTM started with pid=4, OS id=4072 at elevated priority"
                     "VKTM running at (10)millisec precision with DBRM quantum (100)ms"
                     "Sat Jan 14 02:39:09 2012"
                     "GEN0 started with pid=5, OS id=4076 "
                     "Sat Jan 14 02:39:09 2012"
                     "DIAG started with pid=6, OS id=4080 "
                     "Sat Jan 14 02:39:09 2012"
                     "DBRM started with pid=7, OS id=4084 "
                     "Sat Jan 14 02:39:09 2012"
                     "DIA0 started with pid=8, OS id=4088 "
                     "Sat Jan 14 02:39:09 2012"
                     "MMAN started with pid=9, OS id=4092 "
                     "Sat Jan 14 02:39:09 2012"
                     "DBW0 started with pid=10, OS id=268 "
                     "Sat Jan 14 02:39:09 2012"
                     "LGWR started with pid=11, OS id=264 "
                     "Sat Jan 14 02:39:09 2012"
                     "CKPT started with pid=12, OS id=244 "]]]]
   [1326476350000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:39:10 2012"
                     "SMON started with pid=13, OS id=172 "
                     "Sat Jan 14 02:39:10 2012"
                     "RECO started with pid=14, OS id=252 "
                     "Sat Jan 14 02:39:10 2012"
                     "MMON started with pid=15, OS id=272 "
                     "Sat Jan 14 02:39:10 2012"
                     "MMNL started with pid=16, OS id=284 "
                     "Sat Jan 14 02:39:10 2012"
                     "starting up 1 dispatcher(s) for network address '(ADDRESS=(PARTIAL=YES)(PROTOCOL=TCP))'..."
                     "starting up 4 shared server(s) ..."
                     "ORACLE_BASE from environment = C:\\oraclexe\\app\\oracle"]]]]
   [1326476362000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:39:22 2012"
                     "alter database mount exclusive"]]]]
   [1326476368000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:39:28 2012"
                     "Successful mount of redo thread 1, with mount id 2653644186"
                     "Database mounted in Exclusive Mode"
                     "Lost write protection disabled"]]]]
   [1326476380000 [["test/clogs/test/file/alert.log"
                    ["Sat Jan 14 02:39:40 2012"
                     "Completed: alter database mount exclusive"
                     "alter database open"]]]]])

(def trace-clogs-seq
  [[nil [["test/clogs/test/file/trace.trc"
         ["Trace file C:\\oraclexe\\app\\oracle\\diag\\rdbms\\xe\\xe\\trace\\xe_ora_780.trc"
          "Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production"
          "Windows XP Version V5.1 Service Pack 3 "
          "CPU                 : 1 - type 586, 1 Physical Cores"
          "Process Affinity    : 0x0x00000000"
          "Memory (Avail/Total): Ph:362M/767M, Ph+PgF:988M/1490M, VA:1593M/2047M "
          "VM name             : VMWare Version (6)"
          "Instance name: xe"
          "Redo thread mounted by this instance: 0 <none>"
          "Oracle process number: 0"
          "Windows thread id: 780, image: ORACLE.EXE (SHAD)"
          ""
          ""]]]]
  [1326476328187 [["test/clogs/test/file/trace.trc"
                   ["*** 2012-01-14 02:38:48.187"
                    "kcrfwy: minimum sleep is 26801 usecs (overhead is 0 usecs)"
                    "Running with 1 strand for Non-Enterprise Edition"
                    "Running without dynamic strands for Non-Enterprise Edition"
                    ""]]]]
  [1326476348015 [["test/clogs/test/file/trace.trc"
                   ["*** 2012-01-14 02:39:08.015"
                    "*** SESSION ID:(1.1) 2012-01-14 02:39:08.015"
                    "*** SERVICE NAME:() 2012-01-14 02:39:08.015"
                    " "
                    "DBRM(kskinitrm): cpu_count = 1"]]]]])

(def listener-clogs-seq
  [[nil [["test/clogs/test/file/listener.log" []]]]
   [1326476243000 [["test/clogs/test/file/listener.log"
                    ["Sat Jan 14 02:37:23 2012"
                     "System parameter file is C:\\oraclexe\\app\\oracle\\product\\11.2.0\\server\\network\\admin\\listener.ora"
                     "Log messages written to C:\\oraclexe\\app\\oracle\\diag\\tnslsnr\\browning\\listener\\alert\\log.xml"
                     "Trace information written to C:\\oraclexe\\app\\oracle\\diag\\tnslsnr\\browning\\listener\\trace\\ora_500_584.trc"
                     "Trace level is currently 0"
                     ""
                     "pid=500で起動しました"
                     "リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(PIPENAME=\\\\.\\pipe\\EXTPROC1ipc)))"
                     "リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=1521)))"
                     "Listener completed notification to CRS on start"
                     ""
                     "TIMESTAMP * CONNECT DATA [* PROTOCOL INFO] * EVENT [* SID] * RETURN CODE"]]]]
   [1326476348000 [["test/clogs/test/file/listener.log"
                    ["Sat Jan 14 02:39:08 2012"
                     "Dynamic address is already listened on (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=1521)))"
                     "14-1月 -2012 02:39:08 * service_register * xe * 0"]]]]
   [1326476351000 [["test/clogs/test/file/listener.log"
                    ["14-1月 -2012 02:39:11 * service_update * xe * 0"]]]]
   [1326476368000 [["test/clogs/test/file/listener.log"
                    ["Sat Jan 14 02:39:28 2012"
                     "14-1月 -2012 02:39:28 * service_update * xe * 0"]]]]
   [1326476410000 [["test/clogs/test/file/listener.log"
                    ["Sat Jan 14 02:40:10 2012"
                     "14-1月 -2012 02:40:10 * service_update * xe * 0"]]]]
   [1326476419000 [["test/clogs/test/file/listener.log"
                    ["14-1月 -2012 02:40:19 * service_update * xe * 0"]]]]
   [1326476422000 [["test/clogs/test/file/listener.log"
                    ["Sat Jan 14 02:40:22 2012"
                     "リスニングしています: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=browning)(PORT=8080))(Presentation=HTTP)(Session=RAW))"
                     "14-1月 -2012 02:40:22 * service_update * xe * 0"]]]]])