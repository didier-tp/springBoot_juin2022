cd "%~dp0"
set MAVEN_HOME=C:\Prog\apache-maven-3.8.4
set JAVA_HOME=C:\Prog\java\open-jdk\openjdk-17_windows-x64_bin\jdk-17
"%MAVEN_HOME%\bin\mvn" -DskipTests clean install > resBuild.txt 2>errBuild.txt