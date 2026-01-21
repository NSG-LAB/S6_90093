# Spring DI Demo - Build and Run Script
# This script compiles and runs the Spring DI project without Maven

param(
    [string]$action = "build",  # build, run-xml, run-annotation, or all
    [switch]$skipDownload = $false,
    [switch]$quiet = $false
)

$ErrorActionPreference = "Stop"

function Write-Log {
    param([string]$message)
    if (-not $quiet) {
        Write-Host $message
    }
}

function Write-Success {
    param([string]$message)
    Write-Host "✓ $message" -ForegroundColor Green
}

function Write-Error-Custom {
    param([string]$message)
    Write-Host "✗ $message" -ForegroundColor Red
}

# Create directories
Write-Log "Creating directories..."
$null = New-Item -ItemType Directory -Path "target/classes" -Force
$null = New-Item -ItemType Directory -Path "lib" -Force

# Define Spring JARs to download
$springJars = @(
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar"; name = "spring-core-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar"; name = "spring-beans-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar"; name = "spring-context-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar"; name = "spring-jcl-6.1.0.jar" },
    @{ url = "https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar"; name = "commons-logging-1.2.jar" }
)

# Download JARs if not present
if (-not $skipDownload) {
    Write-Log "Checking for Spring JARs..."
    foreach ($jar in $springJars) {
        $jarPath = Join-Path "lib" $jar.name
        if (-not (Test-Path $jarPath)) {
            Write-Log "Downloading $($jar.name)..."
            try {
                Invoke-WebRequest -Uri $jar.url -OutFile $jarPath -ErrorAction Stop
                Write-Success "Downloaded $($jar.name)"
            }
            catch {
                Write-Error-Custom "Failed to download $($jar.name): $_"
                Write-Log "You can manually download from: $($jar.url)"
            }
        }
    }
}

# Build classpath
$libJars = Get-ChildItem "lib" -Filter "*.jar" -ErrorAction SilentlyContinue
if ($libJars.Count -eq 0) {
    Write-Error-Custom "No JAR files found in lib directory. Please download Spring JARs or install Maven."
    exit 1
}

$classPath = ($libJars | ForEach-Object { $_.FullName }) -join ";"

Write-Log "Classpath ready with $($libJars.Count) JAR files"

# Define source files
$sourceFiles = @(
    "src/main/java/com/example/Student.java",
    "src/main/java/com/example/ApplicationConfig.java",
    "src/main/java/com/example/MainXML.java",
    "src/main/java/com/example/MainAnnotation.java"
)

# Compile function
function Compile-Project {
    Write-Log ""
    Write-Log "======================================"
    Write-Log "COMPILING PROJECT"
    Write-Log "======================================"
    
    try {
        $javacArgs = @(
            "-cp", $classPath,
            "-d", "target/classes"
        ) + $sourceFiles
        
        & javac @javacArgs
        
        if ($LASTEXITCODE -eq 0) {
            Write-Success "Compilation successful!"
            return $true
        }
        else {
            Write-Error-Custom "Compilation failed with exit code $LASTEXITCODE"
            return $false
        }
    }
    catch {
        Write-Error-Custom "Compilation error: $_"
        return $false
    }
}

# Run XML demo function
function Run-XML-Demo {
    Write-Log ""
    Write-Log "======================================"
    Write-Log "RUNNING XML CONFIGURATION DEMO"
    Write-Log "======================================"
    Write-Log ""
    
    try {
        & java -cp "target/classes;$classPath" "com.example.MainXML"
        
        if ($LASTEXITCODE -eq 0) {
            Write-Success "XML demo completed successfully"
        }
        else {
            Write-Error-Custom "XML demo failed"
        }
    }
    catch {
        Write-Error-Custom "Error running XML demo: $_"
    }
}

# Run Annotation demo function
function Run-Annotation-Demo {
    Write-Log ""
    Write-Log "======================================"
    Write-Log "RUNNING ANNOTATION CONFIGURATION DEMO"
    Write-Log "======================================"
    Write-Log ""
    
    try {
        & java -cp "target/classes;$classPath" "com.example.MainAnnotation"
        
        if ($LASTEXITCODE -eq 0) {
            Write-Success "Annotation demo completed successfully"
        }
        else {
            Write-Error-Custom "Annotation demo failed"
        }
    }
    catch {
        Write-Error-Custom "Error running Annotation demo: $_"
    }
}

# Main execution
Write-Log ""
Write-Log "╔════════════════════════════════════════╗"
Write-Log "║  Spring Dependency Injection Demo      ║"
Write-Log "║  Build & Run Script                    ║"
Write-Log "╚════════════════════════════════════════╝"

switch ($action.ToLower()) {
    "build" {
        Compile-Project
    }
    "run-xml" {
        if (Compile-Project) {
            Run-XML-Demo
        }
    }
    "run-annotation" {
        if (Compile-Project) {
            Run-Annotation-Demo
        }
    }
    "all" {
        if (Compile-Project) {
            Run-XML-Demo
            Run-Annotation-Demo
        }
    }
    default {
        Write-Host "Usage:"
        Write-Host "  .\build.ps1 build              # Compile only"
        Write-Host "  .\build.ps1 run-xml            # Compile and run XML demo"
        Write-Host "  .\build.ps1 run-annotation     # Compile and run Annotation demo"
        Write-Host "  .\build.ps1 all                # Compile and run both demos"
        Write-Host ""
        Write-Host "Options:"
        Write-Host "  -skipDownload    # Skip downloading JARs (use existing lib/)"
        Write-Host "  -quiet          # Suppress output messages"
    }
}

Write-Log ""
Write-Log "Done!"
