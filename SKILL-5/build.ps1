# Spring Autowiring Demo - PowerShell Build Script
# This script compiles and optionally runs the Spring Autowiring Demo

param(
    [ValidateSet("build", "run-xml", "run-annotation", "clean", "package")]
    [string]$action = "build"
)

function Build-Project {
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Building Spring Autowiring Demo Project" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    mvn clean compile
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "======================================" -ForegroundColor Green
        Write-Host "Build completed successfully!" -ForegroundColor Green
        Write-Host "======================================" -ForegroundColor Green
        Write-Host ""
        Write-Host "Next steps:" -ForegroundColor Yellow
        Write-Host "  - Run XML demo:        .\build.ps1 -action run-xml" -ForegroundColor Yellow
        Write-Host "  - Run Java config:     .\build.ps1 -action run-annotation" -ForegroundColor Yellow
        Write-Host "  - Package JAR:         .\build.ps1 -action package" -ForegroundColor Yellow
    } else {
        Write-Host ""
        Write-Host "=====================================" -ForegroundColor Red
        Write-Host "Build failed! Check errors above." -ForegroundColor Red
        Write-Host "=====================================" -ForegroundColor Red
        exit 1
    }
}

function Run-XML-Demo {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Running XML Configuration Demo" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "======================================" -ForegroundColor Green
        Write-Host "XML Demo executed successfully!" -ForegroundColor Green
        Write-Host "======================================" -ForegroundColor Green
    } else {
        Write-Host ""
        Write-Host "=====================================" -ForegroundColor Red
        Write-Host "Execution failed! Build first:" -ForegroundColor Red
        Write-Host "  .\build.ps1" -ForegroundColor Red
        Write-Host "=====================================" -ForegroundColor Red
        exit 1
    }
}

function Run-Annotation-Demo {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Running Java Annotation Configuration Demo" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "======================================" -ForegroundColor Green
        Write-Host "Annotation Demo executed successfully!" -ForegroundColor Green
        Write-Host "======================================" -ForegroundColor Green
    } else {
        Write-Host ""
        Write-Host "=====================================" -ForegroundColor Red
        Write-Host "Execution failed! Build first:" -ForegroundColor Red
        Write-Host "  .\build.ps1" -ForegroundColor Red
        Write-Host "=====================================" -ForegroundColor Red
        exit 1
    }
}

function Clean-Build {
    Write-Host "Cleaning build artifacts..." -ForegroundColor Yellow
    mvn clean
    Write-Host "Clean completed." -ForegroundColor Green
}

function Package-Project {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host "Packaging Spring Autowiring Demo" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    
    mvn clean package
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "======================================" -ForegroundColor Green
        Write-Host "Package created successfully!" -ForegroundColor Green
        Write-Host "======================================" -ForegroundColor Green
        Write-Host ""
        Write-Host "JAR Location: target/spring-autowiring-demo-1.0.0.jar" -ForegroundColor Yellow
        Write-Host "Run with: java -cp target/spring-autowiring-demo-1.0.0.jar com.example.XMLConfigMain" -ForegroundColor Yellow
    } else {
        Write-Host ""
        Write-Host "=====================================" -ForegroundColor Red
        Write-Host "Package failed! Check errors above." -ForegroundColor Red
        Write-Host "=====================================" -ForegroundColor Red
        exit 1
    }
}

# Execute the requested action
switch ($action) {
    "build" { Build-Project }
    "run-xml" { Build-Project; Run-XML-Demo }
    "run-annotation" { Build-Project; Run-Annotation-Demo }
    "clean" { Clean-Build }
    "package" { Package-Project }
    default { Build-Project }
}
