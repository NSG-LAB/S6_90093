@echo off
REM Spring Autowiring Demo - Build and Run Script
REM This script compiles the project and runs both XML and Annotation configuration demos

setlocal enabledelayedexpansion

echo.
echo ======================================
echo Spring Autowiring Demo
echo Build and Run Script
echo ======================================
echo.

REM Check for Spring JARs in lib folder
set libDir=%~dp0lib
set jarCount=0

if exist "%libDir%" (
    for %%F in ("%libDir%\*.jar") do set /a jarCount+=1
)

if %jarCount% equ 0 (
    echo Checking for Spring JARs...
    echo Warning: No JAR files found in lib folder
    echo.
    echo Please download the following JARs and place them in lib folder:
    echo - spring-core-6.1.0.jar
    echo - spring-beans-6.1.0.jar
    echo - spring-context-6.1.0.jar
    echo - spring-aop-6.1.0.jar
    echo - spring-jcl-6.1.0.jar
    echo - commons-logging-1.2.jar
    echo.
    echo Or run: mvn clean compile
    echo.
    pause
    exit /b 1
)

echo Found %jarCount% JAR files
echo.

REM Build classpath from lib folder
set classpath=target\classes
for %%F in ("%libDir%\*.jar") do set "classpath=!classpath!;%%F"

REM Create a temporary batch file to build the classpath dynamically
setlocal enabledelayedexpansion

REM Compile Java source files
echo ======================================
echo COMPILING PROJECT
echo ======================================
echo.

if not exist "target\classes" mkdir "target\classes"

REM Get source files
set sourceFiles=
for /R "src\main\java" %%F in (*.java) do (
    set "sourceFiles=!sourceFiles! "%%F""
)

REM Compile with classpath
javac -d target\classes -cp "%classpath%" %sourceFiles% 2>&1

if errorlevel 1 (
    echo Compilation failed
    pause
    exit /b 1
)

echo Compilation successful
echo.

REM Copy resources
echo Copying resources...
if exist "src\main\resources" (
    xcopy "src\main\resources\*.*" "target\classes\" /Y /Q
)
echo.

REM Run XML Configuration Demo
echo ======================================
echo RUNNING XML CONFIGURATION DEMO
echo ======================================
echo.

java -cp "%classpath%" com.example.XMLConfigMain
if errorlevel 1 (
    echo XML Configuration demo failed
)

echo.
echo XML demo completed
echo.

REM Run Annotation Configuration Demo
echo ======================================
echo RUNNING ANNOTATION CONFIGURATION DEMO
echo ======================================
echo.

java -cp "%classpath%" com.example.AnnotationConfigMain
if errorlevel 1 (
    echo Annotation Configuration demo failed
)

echo.
echo Annotation demo completed
echo.

echo All done
pause
