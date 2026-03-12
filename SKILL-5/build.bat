@echo off
REM Build script for Spring Autowiring Demo
REM This script compiles the project using Maven

echo Building Spring Autowiring Demo Project...
echo.

mvn clean compile

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ====================================
    echo Build completed successfully!
    echo ====================================
    echo.
    echo To run the project, use:
    echo   mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
    echo   or
    echo   mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
) else (
    echo.
    echo ====================================
    echo Build failed! Please check the errors above.
    echo ====================================
)

pause
