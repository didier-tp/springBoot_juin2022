cd "%~dp0"
set MYSQL_HOME=C:\Program Files\MariaDB 10.6
"%MYSQL_HOME%\bin\mysql" -u root -p  < viewDB.sql
pause