@echo off
chcp 65001 > nul
cls
echo ======================================================================
echo   StarLog Logistics - Compilando e Executando (Aula 24)
echo ======================================================================
where javac >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERRO] javac nao encontrado no PATH!
    pause
    exit /b 1
)
if not exist "bin" mkdir bin
javac -encoding UTF-8 -cp "lib/*" -d bin src/br/com/starlog/model/*.java src/br/com/starlog/view/*.java src/br/com/starlog/controller/*.java src/br/com/starlog/main/*.java
if %errorlevel% neq 0 (
    echo [ERRO] Falha na compilacao.
    pause
    exit /b 1
)
java -cp "bin;lib/*" br.com.starlog.main.MainApp
pause
