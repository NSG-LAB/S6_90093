# Spring Autowiring Demo - PowerShell Build and Run Script

$ErrorActionPreference = "Stop"

function Write-Section {
    param([string]$Title)
    Write-Host ""
    Write-Host "======================================"
    Write-Host $Title
    Write-Host "======================================"
    Write-Host ""
}

# Main execution
Write-Section "Spring Autowiring Demo - Build and Run Script"

# Check for Spring JARs
$libDir = Join-Path (Split-Path -Parent $MyInvocation.MyCommand.Path) "lib"
$libJars = @()

if (Test-Path $libDir) {
    $libJars = @(Get-ChildItem -Path $libDir -Filter "*.jar" -ErrorAction SilentlyContinue)
}

if ($libJars.Count -eq 0) {
    Write-Host "Warning: No JAR files found in lib folder" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Please download the following JARs and place them in lib folder:"
    Write-Host "- spring-core-6.1.0.jar"
    Write-Host "- spring-beans-6.1.0.jar"
    Write-Host "- spring-context-6.1.0.jar"
    Write-Host "- spring-aop-6.1.0.jar"
    Write-Host "- spring-jcl-6.1.0.jar"
    Write-Host "- commons-logging-1.2.jar"
    Write-Host ""
    Write-Host "Or run: mvn clean compile"
    Read-Host "Press any key to continue"
    exit 1
}

Write-Host "Found $($libJars.Count) JAR files" -ForegroundColor Green
Write-Host ""

# Build classpath
$classpath = "target\classes"
foreach ($jar in $libJars) {
    $classpath += ";$($jar.FullName)"
}

# Create target directory
if (-not (Test-Path "target\classes")) {
    New-Item -ItemType Directory -Path "target\classes" -Force | Out-Null
}

# Compile
Write-Section "COMPILING PROJECT"

$sourceFiles = @(Get-ChildItem -Path "src\main\java" -Filter "*.java" -Recurse)

if ($sourceFiles.Count -eq 0) {
    Write-Host "No source files found" -ForegroundColor Red
    exit 1
}

$fileList = $sourceFiles | ForEach-Object { $_.FullName }
javac -d target\classes -cp $classpath $fileList 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compilation failed" -ForegroundColor Red
    exit 1
}

Write-Host "Compilation successful" -ForegroundColor Green
Write-Host ""

# Copy resources
Write-Host "Copying resources..."
if (Test-Path "src\main\resources") {
    Copy-Item -Path "src\main\resources\*" -Destination "target\classes\" -Recurse -Force -ErrorAction SilentlyContinue
}
Write-Host ""

# Run XML Configuration Demo
Write-Section "RUNNING XML CONFIGURATION DEMO"

java -cp $classpath com.example.XMLConfigMain 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host "XML Configuration demo failed" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "XML demo completed" -ForegroundColor Green
Write-Host ""

# Run Annotation Configuration Demo
Write-Section "RUNNING ANNOTATION CONFIGURATION DEMO"

java -cp $classpath com.example.AnnotationConfigMain 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host "Annotation Configuration demo failed" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Annotation demo completed" -ForegroundColor Green
Write-Host ""

Write-Host "All done" -ForegroundColor Green
Read-Host "Press any key to continue"
