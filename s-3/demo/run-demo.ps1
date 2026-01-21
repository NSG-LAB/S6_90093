# Spring DI Demo - Build Script with Auto-Download
# Downloads Spring JARs if needed, then compiles and runs the project

$ErrorActionPreference = "Stop"

Write-Host ""
Write-Host "╔════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║ Spring Dependency Injection Demo       ║" -ForegroundColor Cyan
Write-Host "║ Build & Run Script (with JAR Download) ║" -ForegroundColor Cyan
Write-Host "╚════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Create directories
Write-Host "Creating directories..." -ForegroundColor Yellow
New-Item -ItemType Directory -Path "target/classes" -Force | Out-Null
New-Item -ItemType Directory -Path "lib" -Force | Out-Null

# Define Spring JARs to download
Write-Host "Checking for Spring JARs..." -ForegroundColor Yellow

$springJars = @(
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar"; name = "spring-core-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar"; name = "spring-beans-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar"; name = "spring-context-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar"; name = "spring-jcl-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar"; name = "commons-logging-1.2.jar" }
)

# Download missing JARs
foreach ($jar in $springJars) {
    $jarPath = Join-Path "lib" $jar.name
    if (-not (Test-Path $jarPath)) {
        Write-Host "Downloading $($jar.name)..." -ForegroundColor Yellow
        try {
            [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
            Invoke-WebRequest -Uri $jar.url -OutFile $jarPath -ErrorAction Stop
            Write-Host "  ✓ Downloaded" -ForegroundColor Green
        }
        catch {
            Write-Host "  ✗ Failed to download: $_" -ForegroundColor Red
            Write-Host "  Please download manually from: $($jar.url)" -ForegroundColor Yellow
        }
    }
    else {
        Write-Host "  ✓ Found $($jar.name)" -ForegroundColor Green
    }
}

# Build classpath
$libJars = Get-ChildItem "lib" -Filter "*.jar" -ErrorAction SilentlyContinue
if ($libJars.Count -eq 0) {
    Write-Host ""
    Write-Host "ERROR: No JAR files found in lib directory" -ForegroundColor Red
    exit 1
}

$classPath = ($libJars | ForEach-Object { $_.FullName }) -join ";"
Write-Host ""
Write-Host "Ready to compile with $($libJars.Count) JAR files" -ForegroundColor Green

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

javac -cp "$classPath" -d target/classes @sourceFiles 2>&1

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "ERROR: Compilation failed" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "✓ Compilation successful!" -ForegroundColor Green

# Run XML Demo
Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "RUNNING XML CONFIGURATION DEMO" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

java -cp "target/classes;$classPath" com.example.MainXML 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "✓ XML demo completed successfully!" -ForegroundColor Green
}
else {
    Write-Host ""
    Write-Host "✗ XML demo failed" -ForegroundColor Red
}

# Run Annotation Demo
Write-Host ""
Write-Host "======================================" -ForegroundColor Cyan
Write-Host "RUNNING ANNOTATION CONFIGURATION DEMO" -ForegroundColor Cyan
Write-Host "======================================" -ForegroundColor Cyan
Write-Host ""

java -cp "target/classes;$classPath" com.example.MainAnnotation 2>&1

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "✓ Annotation demo completed successfully!" -ForegroundColor Green
}
else {
    Write-Host ""
    Write-Host "✗ Annotation demo failed" -ForegroundColor Red
}

Write-Host ""
Write-Host "══════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "All done!" -ForegroundColor Cyan
Write-Host "══════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""
