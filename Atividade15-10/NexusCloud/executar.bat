@echo off
chcp 65001 > nul
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src/*.java
if %errorlevel% neq 0 ( echo [ERRO] Falha na compilacao. & pause & exit /b 1 )
java -cp bin MainCloud
pause
