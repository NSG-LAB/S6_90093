@echo off
REM Spring DI Demo - Build and Run Script for Windows CMD
REM This script compiles and runs the Spring DI project

setlocal enabledelayedexpansion

REM Create directories
if not exist target\classes mkdir target\classes
if not exist lib mkdir lib

REM Spring JAR URLs
set "JAR_URLS[0]=https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar"
set "JAR_URLS[1]=https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar"
set "JAR_URLS[2]=https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar"
set "JAR_URLS[3]=https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar"
set "JAR_URLS[4]=https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar"

set "JAR_FILES[0]=spring-core-6.1.0.jar"
set "JAR_FILES[1]=spring-beans-6.1.0.jar"
set "JAR_FILES[2]=spring-context-6.1.0.jar"
set "JAR_FILES[3]=spring-jcl-6.1.0.jar"
set "JAR_FILES[4]=commons-logging-1.2.jar"

echo.
echo ======================================
echo Spring Dependency Injection Demo
echo Build ^& Run Script
echo ======================================
echo.

REM Set default action
set ACTION=%1
if "%ACTION%"=="" set ACTION=all

REM Download JARs if needed
echo Checking for Spring JARs...
for /l %%i in (0,1,4) do (
    if not exist lib\!JAR_FILES[%%i]! (
        echo Downloading !JAR_FILES[%%i]!...
        REM Note: You may need to use a tool like curl or wget to download
        REM Or download manually from the URLs above
    )
)

REM Build classpath
set CLASSPATH=
for /f "delims=" %%f in ('dir /b lib\*.jar 2^>nul') do (
    set "CLASSPATH=!CLASSPATH!;lib\%%f"
)

if "%CLASSPATH%"=="" (
    echo ERROR: No JAR files found in lib directory
    echo Please download Spring JARs to lib\ folder
    echo Or install Maven and run: mvn clean compile
    exit /b 1
)

REM Remove leading semicolon
set "CLASSPATH=%CLASSPATH:~1%"

REM Main action handling
if /i "%ACTION%"=="build" goto BUILD
if /i "%ACTION%"=="run-xml" goto RUNXML
if /i "%ACTION%"=="run-annotation" goto RUNANNOTATION
if /i "%ACTION%"=="all" goto BUILD

:USAGE
echo Usage:
echo   build.bat build              - Compile only
echo   build.bat run-xml            - Compile and run XML demo
echo   build.bat run-annotation     - Compile and run Annotation demo
echo   build.bat all                - Compile and run both demos
exit /b 0

:BUILD
echo.
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
    echo [OK] Compilation successful!
    if /i "%ACTION%"=="run-xml" goto RUNXML
    if /i "%ACTION%"=="run-annotation" goto RUNANNOTATION
    if /i "%ACTION%"=="all" goto RUNXML
) else (
    echo.
    echo [ERROR] Compilation failed!
    exit /b 1
)
goto END

:RUNXML
echo.
echo ======================================
echo RUNNING XML CONFIGURATION DEMO
echo ======================================
echo.

java -cp "target\classes;%CLASSPATH%" com.example.MainXML

if %ERRORLEVEL% equ 0 (
    echo.
    echo [OK] XML demo completed successfully
    if /i "%ACTION%"=="all" goto RUNANNOTATION
) else (
    echo.
    echo [ERROR] XML demo failed
    exit /b 1
)
goto END

:RUNANNOTATION
echo.
echo ======================================
echo RUNNING ANNOTATION CONFIGURATION DEMO
echo ======================================
echo.

java -cp "target\classes;%CLASSPATH%" com.example.MainAnnotation

if %ERRORLEVEL% equ 0 (
    echo.
    echo [OK] Annotation demo completed successfully
) else (
    echo.
    echo [ERROR] Annotation demo failed
    exit /b 1
)

:END
echo.
echo Done!
