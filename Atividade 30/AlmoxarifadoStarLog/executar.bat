@echo off
chcp 65001 > nul
if not exist bin mkdir bin
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/starlog/model/*.java src/br/com/starlog/factory/*.java src/br/com/starlog/dao/*.java src/br/com/starlog/main/*.java
if %errorlevel% neq 0 ( echo [ERRO] Falha na compilacao. & pause & exit /b 1 )
java -Dfile.encoding=UTF-8 -cp "bin;lib/*" br.com.starlog.main.Main
pause
