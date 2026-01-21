@echo off
REM Run script for Spring Autowiring Demo using Java Configuration

echo.
echo ================================================
echo Spring Autowiring Demo - Java Configuration
echo ================================================
echo.

mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ================================================
    echo Execution completed successfully!
    echo ================================================
) else (
    echo.
    echo ================================================
    echo Execution failed! Build the project first using:
    echo   mvn clean compile
    echo ================================================
)

pause
