cd /d %~dp0
call set_env.bat
set PATH=C:\Program Files\Java\jdk-11.0.4\bin;%PATH%
java -jar  %H2_CLASSPATH% -user "sa" -url %MY_H2_DB_URL%

REM NB: penser à se déconnecter
REM    et options/session actives/arrêt pour éviter des futurs verrous/blocages

pause