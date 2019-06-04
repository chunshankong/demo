SET IDE_HOME=%cd%
echo %IDE_HOME%
SET JAVA_EXE=%IDE_HOME%\jre1.8.0_181\bin\java.exe
SET ALL_JVM_ARGS= -Xmx1024m -Xms1024m -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError  -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9130 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false

:: ---------------------------------------------------------------------
:: Run the robot.
:: ---------------------------------------------------------------------
SET OLD_PATH=%PATH%
SET PATH=%PATH%

"%JAVA_EXE%" -jar %ALL_JVM_ARGS%  demo-1.0.0-SNAPSHOT.jar

SET PATH=%OLD_PATH%