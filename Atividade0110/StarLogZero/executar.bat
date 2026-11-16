@echo off
chcp 65001 > nul
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src/br/com/starlog/model/*.java src/br/com/starlog/exception/*.java src/br/com/starlog/main/*.java
if %errorlevel% neq 0 ( echo [ERRO] Falha na compilacao. & pause & exit /b 1 )
java -cp bin br.com.starlog.main.MainResgate
pause
