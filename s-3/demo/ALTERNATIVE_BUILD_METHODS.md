# Alternative Compilation & Execution Methods

## Option 1: Manual Compilation with javac

### Step 1: Download Spring JAR files

Create a `lib` folder and download these Spring JARs:

```
lib/
├── spring-core-6.1.0.jar
├── spring-beans-6.1.0.jar
├── spring-context-6.1.0.jar
├── spring-jcl-6.1.0.jar
└── commons-logging-1.2.jar
```

**Download from**: https://repo.maven.apache.org/maven2/org/springframework/

### Step 2: Compile Java Source Files

```bash
# Create output directory
mkdir -p target\classes

# Compile all Java files with Spring JARs in classpath
javac -cp "lib\*" -d target\classes ^
  src\main\java\com\example\Student.java ^
  src\main\java\com\example\ApplicationConfig.java ^
  src\main\java\com\example\MainXML.java ^
  src\main\java\com\example\MainAnnotation.java

# For Linux/Mac:
javac -cp "lib/*" -d target/classes \
  src/main/java/com/example/Student.java \
  src/main/java/com/example/ApplicationConfig.java \
  src/main/java/com/example/MainXML.java \
  src/main/java/com/example/MainAnnotation.java
```

### Step 3: Run the Application

```bash
# Run XML Demo
java -cp "target\classes;lib\*" com.example.MainXML

# Run Annotation Demo
java -cp "target\classes;lib\*" com.example.MainAnnotation

# For Linux/Mac:
java -cp "target/classes:lib/*" com.example.MainXML
java -cp "target/classes:lib/*" com.example.MainAnnotation
```

---

## Option 2: Using Gradle (if installed)

### Install Gradle

```bash
# Windows: Download from https://gradle.org/releases/
# Extract and add to PATH

# Verify installation
gradle -version
```

### Create build.gradle

```gradle
plugins {
    id 'java'
}

group = 'maven'
version = '1.0-SNAPSHOT'
sourceCompatibility = '21'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework:spring-core:6.1.0'
    implementation 'org.springframework:spring-beans:6.1.0'
    implementation 'org.springframework:spring-context:6.1.0'
}

tasks.register('runXML', JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.MainXML'
}

tasks.register('runAnnotation', JavaExec) {
    classpath = sourceSets.main.runtimeClasspath
    mainClass = 'com.example.MainAnnotation'
}
```

### Run with Gradle

```bash
# Build
gradle build

# Run XML Demo
gradle runXML

# Run Annotation Demo
gradle runAnnotation
```

---

## Option 3: Using an IDE (Recommended)

### Eclipse

1. **Create Maven Project**:
   - File → New → Maven Project
   - Select `maven-archetype-quickstart`
   - Group ID: `com.example`
   - Artifact ID: `spring-di-demo`

2. **Import Project**:
   - File → Open Projects from File System
   - Select demo folder

3. **Add Dependencies**:
   - Right-click project → Maven → Add Dependency
   - Add spring-core, spring-beans, spring-context (v6.1.0)

4. **Copy Source Files**:
   - Copy .java files to `src/main/java/com/example/`
   - Copy applicationContext.xml to `src/main/resources/`

5. **Run**:
   - Right-click MainXML.java → Run As → Java Application
   - Right-click MainAnnotation.java → Run As → Java Application

### IntelliJ IDEA

1. **Create Project**:
   - New Project → Java
   - Set up JDK 21

2. **Create Module Structure**:
   - File → Project Structure → Modules
   - Create `src/main/java` and `src/main/resources`

3. **Add Spring Dependencies**:
   - File → Project Structure → Libraries
   - Add spring-core, spring-beans, spring-context JARs

4. **Copy Files**:
   - Copy .java files and applicationContext.xml

5. **Run**:
   - Right-click MainXML.java → Run
   - Right-click MainAnnotation.java → Run

### VS Code

1. **Install Extensions**:
   - Extension Pack for Java
   - Spring Boot Extension Pack

2. **Configure**:
   - Create `.vscode/launch.json` for debugging
   - Create `.vscode/tasks.json` for building

3. **Open Terminal**:
   - Ctrl + ~

4. **Build and Run**:
   - Use commands from Option 1 (javac)

---

## Option 4: PowerShell Build Script

Create `build.ps1`:

```powershell
# Download Spring JARs (if not present)
$libDir = "lib"
if (-not (Test-Path $libDir)) {
    Write-Host "Creating lib directory..."
    New-Item -ItemType Directory -Path $libDir
    
    $jars = @(
        "https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar",
        "https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar",
        "https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar",
        "https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar",
        "https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar"
    )
    
    foreach ($jar in $jars) {
        $fileName = Split-Path $jar -Leaf
        Write-Host "Downloading $fileName..."
        Invoke-WebRequest -Uri $jar -OutFile "$libDir\$fileName"
    }
}

# Create target directory
New-Item -ItemType Directory -Path "target\classes" -Force | Out-Null

# Compile
Write-Host "Compiling Java files..."
$libPath = (Get-ChildItem lib -Filter "*.jar" | ForEach-Object { $_.FullName }) -join ";"
javac -cp "$libPath" -d target\classes `
    src/main/java/com/example/Student.java `
    src/main/java/com/example/ApplicationConfig.java `
    src/main/java/com/example/MainXML.java `
    src/main/java/com/example/MainAnnotation.java

if ($?) {
    Write-Host "✓ Compilation successful!"
} else {
    Write-Host "✗ Compilation failed!"
    exit 1
}
```

### Run the script:

```bash
.\build.ps1
```

---

## Option 5: Docker (Advanced)

Create `Dockerfile`:

```dockerfile
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy source files
COPY src/ src/
COPY pom.xml .

# Download Maven
RUN apt-get update && apt-get install -y maven

# Build
RUN mvn clean compile -q

# Run XML demo by default
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.example.MainXML"]
```

### Build and Run:

```bash
# Build image
docker build -t spring-di-demo .

# Run XML demo
docker run spring-di-demo

# Run Annotation demo
docker run spring-di-demo mvn exec:java -Dexec.mainClass=com.example.MainAnnotation
```

---

## Option 6: Windows Batch Script

Create `build.bat`:

```batch
@echo off
setlocal enabledelayedexpansion

REM Create target directory
if not exist target\classes mkdir target\classes

REM Set classpath
setlocal
for /f "tokens=*" %%A in ('dir /b lib\*.jar') do (
    set "CLASSPATH=!CLASSPATH!;lib\%%A"
)

REM Compile
echo Compiling...
javac -cp "%CLASSPATH%" -d target\classes ^
    src\main\java\com\example\Student.java ^
    src\main\java\com\example\ApplicationConfig.java ^
    src\main\java\com\example\MainXML.java ^
    src\main\java\com\example\MainAnnotation.java

if %errorlevel% equ 0 (
    echo Build successful!
    echo.
    echo Run XML demo:
    echo   java -cp "target\classes;lib\*" com.example.MainXML
    echo.
    echo Run Annotation demo:
    echo   java -cp "target\classes;lib\*" com.example.MainAnnotation
) else (
    echo Build failed!
    exit /b 1
)
```

### Run:

```batch
build.bat
```

---

## Option 7: Quick Start with Pre-downloaded JARs

If you have Spring JARs already available:

```bash
# Set variable for classpath
set SPRING_JARS=C:\path\to\spring\jars

# Compile
javac -cp "%SPRING_JARS%\*" -d target\classes `
    src\main\java\com\example\*.java

# Run XML
java -cp "target\classes;%SPRING_JARS%\*" com.example.MainXML

# Run Annotation
java -cp "target\classes;%SPRING_JARS%\*" com.example.MainAnnotation
```

---

## Option 8: IDE Setup without Manual Compilation

### NetBeans

1. **Create New Project**:
   - File → New Project → Java with Ant
   - Set up project structure

2. **Add Libraries**:
   - Right-click project → Properties → Libraries
   - Add Spring JARs

3. **Copy Files** and Run

### BlueJ

1. **Create Project**
2. **Add Source Files**
3. **Compile and Run from IDE**

---

## Recommended Quick Steps

### If you have time to install one tool:
**Install Maven** (easiest, ~5 minutes):
- Download from maven.apache.org
- Add to PATH
- Run: `mvn clean compile` then demo commands

### If you want to run immediately:
**Use Option 4 (PowerShell script)**:
- Automatically downloads JARs
- Compiles code
- Ready to run

### If you want a professional setup:
**Use VS Code + Extension Pack for Java**:
- Install extension
- Press F5 to debug
- Press Ctrl+F5 to run

---

## Summary Table

| Option | Difficulty | Time | Requirements |
|--------|-----------|------|--------------|
| Manual javac | Medium | 10 min | Java JDK, manual JAR download |
| Gradle | Medium | 15 min | Gradle installed |
| Eclipse/IntelliJ | Easy | 20 min | IDE + plugins |
| PowerShell Script | Easy | 5 min | PowerShell, Internet |
| Docker | Hard | 30 min | Docker installed |
| Batch Script | Easy | 5 min | Batch, JAR files |

---

## Troubleshooting

### "Package org.springframework not found"
- Ensure Spring JARs are in classpath
- Check JAR paths are correct

### "Class not found: com.example.MainXML"
- Verify classes compiled to target/classes
- Check package structure matches

### Out of memory errors
- Add memory: `java -Xmx512m -Xms256m -cp ...`

---

Need help with any specific option?
