cd /d "%~dp0/target"
REM NB: in META-INF/MANIFEST.MF of .jar
REM Main-Class: org.springframework.boot.loader.PropertiesLauncher
REM Start-Class: tp.appliSpring.AppliSpringApplication
java -Dspring.profiles.active=reInit,embeddedDb -jar appliSpring.jar
pause