# Spring DI Demo - Simple Build Script for PowerShell
# Compiles and runs Spring DI project

$ErrorActionPreference = "Stop"

Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "Spring Dependency Injection Demo" -ForegroundColor Cyan
Write-Host "Build & Run Script" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

# Create directories
Write-Host "Creating directories..." -ForegroundColor Yellow
New-Item -ItemType Directory -Path "target/classes" -Force | Out-Null
New-Item -ItemType Directory -Path "lib" -Force | Out-Null

# Get JARs
Write-Host "Checking for Spring JARs..." -ForegroundColor Yellow
$libJars = Get-ChildItem "lib" -Filter "*.jar" -ErrorAction SilentlyContinue

if ($libJars.Count -eq 0) {
    Write-Host "ERROR: No JAR files found in lib directory" -ForegroundColor Red
    Write-Host "Please download Spring JARs or install Maven" -ForegroundColor Red
    exit 1
}

$classPath = ($libJars | ForEach-Object { $_.FullName }) -join ";"
Write-Host "Found $($libJars.Count) JAR files" -ForegroundColor Green

# Compile
Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "COMPILING PROJECT" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

$sourceFiles = @(
    "src/main/java/com/example/Student.java",
    "src/main/java/com/example/ApplicationConfig.java",
    "src/main/java/com/example/MainXML.java",
    "src/main/java/com/example/MainAnnotation.java"
)

javac -cp "$classPath" -d target/classes @sourceFiles

if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Compilation failed" -ForegroundColor Red
    exit 1
}

Write-Host "Compilation successful!" -ForegroundColor Green

# Run XML Demo
Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "RUNNING XML CONFIGURATION DEMO" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

java -cp "target/classes;$classPath" com.example.MainXML

Write-Host ""
Write-Host "XML demo completed!" -ForegroundColor Green

# Run Annotation Demo
Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "RUNNING ANNOTATION CONFIGURATION DEMO" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

java -cp "target/classes;$classPath" com.example.MainAnnotation

Write-Host ""
Write-Host "Annotation demo completed!" -ForegroundColor Green
Write-Host ""
Write-Host "Done!" -ForegroundColor Cyan
