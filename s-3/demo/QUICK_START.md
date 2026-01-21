# Quick Start Guide - No Maven Required!

## 🚀 Choose Your Platform

### Windows (PowerShell)

**Step 1: Open PowerShell**
```powershell
# Navigate to project
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo

# Enable script execution (if needed, run once)
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

**Step 2: Run the build script**
```powershell
# Build only
.\build.ps1 build

# Build and run XML demo
.\build.ps1 run-xml

# Build and run Annotation demo
.\build.ps1 run-annotation

# Build and run both demos
.\build.ps1 all
```

**Step 3: Expected Output**
- Script will automatically download Spring JARs to `lib/` folder
- Compile Java files to `target/classes`
- Run the selected demo

---

### Windows (Command Prompt)

**Step 1: Open Command Prompt**
```cmd
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo
```

**Step 2: Run the build script**
```cmd
REM Build only
build.bat build

REM Build and run XML demo
build.bat run-xml

REM Build and run Annotation demo
build.bat run-annotation

REM Build and run both demos
build.bat all
```

**Note**: For CMD, you may need to manually download JARs or use PowerShell/WSL

---

### Linux / macOS

**Step 1: Open Terminal**
```bash
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo
chmod +x build.sh  # Make script executable
```

**Step 2: Run the build script**
```bash
# Build only
./build.sh build

# Build and run XML demo
./build.sh run-xml

# Build and run Annotation demo
./build.sh run-annotation

# Build and run both demos
./build.sh all
```

---

## 📋 What Each Command Does

### `build` (Compile Only)
```
1. Downloads Spring JARs to lib/ (if missing)
2. Compiles all Java files
3. Places .class files in target/classes
```

### `run-xml` (Build + XML Demo)
```
1. Compiles the project
2. Runs MainXML class
3. Shows Constructor Injection (XML)
4. Shows Setter Injection (XML)
```

### `run-annotation` (Build + Annotation Demo)
```
1. Compiles the project
2. Runs MainAnnotation class
3. Shows Constructor Injection (Annotation)
4. Shows Setter Injection (Annotation)
```

### `all` (Build + Both Demos)
```
1. Compiles the project
2. Runs both XML and Annotation demos
```

---

## 💡 Expected Output Example

When running `all`:

```
======================================
Spring Dependency Injection Demo
Build & Run Script
======================================

Checking for Spring JARs...
[Downloading JARs if needed...]

======================================
COMPILING PROJECT
======================================

✓ Compilation successful!

======================================
RUNNING XML CONFIGURATION DEMO
======================================

=== Spring XML-based Dependency Injection Demo ===

--- Constructor Injection (XML Configuration) ---
Bean Retrieved: Student{studentId=101, name='John Doe', course='Computer Science', year=2}
Student ID: 101
Name: John Doe
Course: Computer Science
Year: 2

--- Setter Injection (XML Configuration) ---
Bean Retrieved: Student{studentId=102, name='Jane Smith', course='Information Technology', year=3}
Student ID: 102
Name: Jane Smith
Course: Information Technology
Year: 3

=== Application Closed ===

✓ XML demo completed successfully

======================================
RUNNING ANNOTATION CONFIGURATION DEMO
======================================

=== Spring Annotation-based Dependency Injection Demo ===

--- Constructor Injection (Annotation Configuration) ---
Bean Retrieved: Student{studentId=201, name='Alice Johnson', course='Electronics', year=1}
Student ID: 201
Name: Alice Johnson
Course: Electronics
Year: 1

--- Setter Injection (Annotation Configuration) ---
Bean Retrieved: Student{studentId=202, name='Bob Wilson', course='Mechanical Engineering', year=4}
Student ID: 202
Name: Bob Wilson
Course: Mechanical Engineering
Year: 4

=== Application Closed ===

✓ Annotation demo completed successfully

Done!
```

---

## 🔧 Manual Compilation (if scripts fail)

If the scripts don't work, compile manually:

### Windows PowerShell
```powershell
# Create directories
mkdir target/classes -Force

# Download Spring JARs manually or find them in lib/
# Then compile:
$libPath = (Get-ChildItem lib -Filter "*.jar" | ForEach-Object { $_.FullName }) -join ";"
javac -cp "$libPath" -d target/classes `
    src/main/java/com/example/Student.java `
    src/main/java/com/example/ApplicationConfig.java `
    src/main/java/com/example/MainXML.java `
    src/main/java/com/example/MainAnnotation.java

# Run XML demo
java -cp "target/classes;$libPath" com.example.MainXML

# Run Annotation demo
java -cp "target/classes;$libPath" com.example.MainAnnotation
```

### Linux / macOS
```bash
# Create directories
mkdir -p target/classes lib

# Download Spring JARs manually or find them in lib/
# Then compile:
libPath=$(ls lib/*.jar | tr '\n' ':')
javac -cp "$libPath" -d target/classes \
    src/main/java/com/example/Student.java \
    src/main/java/com/example/ApplicationConfig.java \
    src/main/java/com/example/MainXML.java \
    src/main/java/com/example/MainAnnotation.java

# Run XML demo
java -cp "target/classes:$libPath" com.example.MainXML

# Run Annotation demo
java -cp "target/classes:$libPath" com.example.MainAnnotation
```

---

## ⚠️ Troubleshooting

### Error: "No JAR files found"
**Solution**: 
- Scripts need Internet to download JARs
- Or manually download from:
  - https://repo.maven.apache.org/maven2/org/springframework/
  - Place in `lib/` folder
- Required JARs:
  - spring-core-6.1.0.jar
  - spring-beans-6.1.0.jar
  - spring-context-6.1.0.jar
  - spring-jcl-6.1.0.jar
  - commons-logging-1.2.jar

### Error: "javac command not found"
**Solution**: Install Java 21 from https://www.oracle.com/java/

### Error: "Cannot find symbol"
**Solution**: Ensure all source files are present in `src/main/java/com/example/`

### Script won't execute (PowerShell)
**Solution**: Run this once in PowerShell:
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### Script permissions (Linux/Mac)
**Solution**: Make script executable:
```bash
chmod +x build.sh
```

---

## 📚 Project Structure After Build

```
s-3/demo/
├── src/
│   └── main/java/com/example/
│       ├── Student.java
│       ├── ApplicationConfig.java
│       ├── MainXML.java
│       └── MainAnnotation.java
├── target/
│   └── classes/                    ← Compiled .class files
│       └── com/example/
│           ├── Student.class
│           ├── ApplicationConfig.class
│           ├── MainXML.class
│           └── MainAnnotation.class
├── lib/                            ← Downloaded Spring JARs
│   ├── spring-core-6.1.0.jar
│   ├── spring-beans-6.1.0.jar
│   ├── spring-context-6.1.0.jar
│   ├── spring-jcl-6.1.0.jar
│   └── commons-logging-1.2.jar
└── build.ps1, build.bat, build.sh ← Build scripts
```

---

## 🎯 Next Steps

1. **Run the build script** for your platform
2. **Review the output** to understand Spring DI
3. **Modify bean values** in:
   - `src/main/resources/applicationContext.xml`
   - `src/main/java/com/example/ApplicationConfig.java`
4. **Re-run** to see changes

---

## 📖 For More Information

- **ALTERNATIVE_BUILD_METHODS.md** - More build options (Gradle, IDE, Docker)
- **SPRING_DI_README.md** - Spring DI concepts
- **QUICK_REFERENCE.md** - Code examples
- **SETUP_GUIDE.md** - Detailed setup instructions

---

**Ready to run!** Choose your platform script above and execute.
