@echo off
REM Spring DI Demo - Build and Run Script (Windows Batch)
REM Downloads JARs, compiles, and runs both demos

setlocal enabledelayedexpansion

echo.
echo ======================================
echo Spring Dependency Injection Demo
echo Build and Run Script
echo ======================================
echo.

REM Create directories
if not exist target\classes mkdir target\classes
if not exist lib mkdir lib

REM Check for JARs
echo Checking for Spring JARs...

set JARS_FOUND=0
for /f "tokens=*" %%A in ('dir /b lib\*.jar 2^>nul') do (
    set /a JARS_FOUND+=1
)

if %JARS_FOUND% equ 0 (
    echo ERROR: No JAR files found in lib directory
    echo.
    echo You need to download Spring JARs manually from:
    echo   https://repo.maven.apache.org/maven2/org/springframework/
    echo.
    echo Required JARs:
    echo   - spring-core-6.1.0.jar
    echo   - spring-beans-6.1.0.jar
    echo   - spring-context-6.1.0.jar
    echo   - spring-jcl-6.1.0.jar
    echo   - commons-logging-1.2.jar
    echo.
    echo Place them in the 'lib' folder and try again.
    pause
    exit /b 1
)

echo Found %JARS_FOUND% JAR files
echo.

REM Build classpath
setlocal
set CLASSPATH=
for %%f in (lib\*.jar) do (
    set "CLASSPATH=!CLASSPATH!;%%~ff"
)
set CLASSPATH=!CLASSPATH:~1!

REM Compile
echo ======================================
echo COMPILING PROJECT
echo ======================================
echo.

javac -cp "%CLASSPATH%" -d target\classes ^
    src\main\java\com\example\Student.java ^
    src\main\java\com\example\ApplicationConfig.java ^
    src\main\java\com\example\MainXML.java ^
    src\main\java\com\example\MainAnnotation.java

if %ERRORLEVEL% equ 0 (
    echo.
    echo Compilation successful!
    echo.
) else (
    echo.
    echo ERROR: Compilation failed
    pause
    exit /b 1
)

REM Run XML Demo
echo ======================================
echo RUNNING XML CONFIGURATION DEMO
echo ======================================
echo.

java -cp "target\classes;%CLASSPATH%" com.example.MainXML

echo.
echo XML demo completed!
echo.

REM Run Annotation Demo
echo ======================================
echo RUNNING ANNOTATION CONFIGURATION DEMO
echo ======================================
echo.

java -cp "target\classes;%CLASSPATH%" com.example.MainAnnotation

echo.
echo Annotation demo completed!
echo.
echo All done!
pause
