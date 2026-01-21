# Running the Spring DI Project - Complete Guide

## ⚠️ Current Status

**Project**: ✅ Created, Compiled-ready, Git-managed  
**Spring DI Code**: ✅ Complete (Student, Config, MainXML, MainAnnotation)  
**Build Scripts**: ✅ Available  
**Missing**: 🔴 Spring JARs (need to download)

---

## 📥 Step 1: Download Spring JARs

You need 5 JAR files for Spring 6.1.0:

### Option A: Download Manually (Recommended)

1. Create a `lib` folder in the project root:
   ```
   c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo\lib\
   ```

2. Download these 5 files and save to `lib/`:
   - [spring-core-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar)
   - [spring-beans-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar)
   - [spring-context-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar)
   - [spring-jcl-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar)
   - [commons-logging-1.2.jar](https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar)

3. Folder structure should be:
   ```
   demo/
   ├── lib/
   │   ├── spring-core-6.1.0.jar
   │   ├── spring-beans-6.1.0.jar
   │   ├── spring-context-6.1.0.jar
   │   ├── spring-jcl-6.1.0.jar
   │   └── commons-logging-1.2.jar
   ├── src/
   └── [other files]
   ```

### Option B: Install Maven (Creates JARs automatically)

```bash
# Download Maven from: https://maven.apache.org/download.cgi
# Add to PATH
# Then run: mvn clean compile
```

---

## 🚀 Step 2: Run the Build Script

Once JARs are in `lib/` folder, run one of these:

### Windows Batch
```cmd
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo
run-demo.bat
```

### Windows PowerShell
```powershell
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo
.\build-simple.ps1
```

### Manual Compilation (Any OS)
```bash
cd c:\Users\sivag\Desktop\FSD\S6_90093\s-3\demo

# Set classpath with all JARs
SET CLASSPATH=lib\spring-core-6.1.0.jar;lib\spring-beans-6.1.0.jar;lib\spring-context-6.1.0.jar;lib\spring-jcl-6.1.0.jar;lib\commons-logging-1.2.jar

# Compile
javac -cp %CLASSPATH% -d target/classes src/main/java/com/example/Student.java src/main/java/com/example/ApplicationConfig.java src/main/java/com/example/MainXML.java src/main/java/com/example/MainAnnotation.java

# Run XML demo
java -cp target/classes;%CLASSPATH% com.example.MainXML

# Run Annotation demo
java -cp target/classes;%CLASSPATH% com.example.MainAnnotation
```

---

## ✅ Expected Output

### XML Configuration Demo
```
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
```

### Annotation Configuration Demo
```
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
```

---

## 📚 Available Build Scripts

| Script | Platform | Usage |
|--------|----------|-------|
| `run-demo.bat` | Windows CMD | `run-demo.bat` |
| `build-simple.ps1` | Windows PowerShell | `.\build-simple.ps1` |
| `build.sh` | Linux/macOS | `./build.sh all` |
| Manual javac | All platforms | See commands above |

---

## 🔧 Troubleshooting

### Error: "No JAR files found"
- **Solution**: Ensure all 5 JARs are in `lib/` folder
- Download them from Maven Central (links provided above)

### Error: "javac command not found"
- **Solution**: Install Java 21 from [oracle.com/java/](https://www.oracle.com/java/)

### Error: "Class not found: com.example.MainXML"
- **Solution**: Ensure compilation succeeded and files are in `target/classes/`

### Error: "Exception in thread 'main' org.springframework.context.support.ClassPathXmlApplicationContext"
- **Solution**: Ensure `src/main/resources/applicationContext.xml` exists

---

## 📋 Quick Checklist

- [ ] Java 21 installed: `java -version`
- [ ] 5 Spring JARs downloaded to `lib/` folder
- [ ] Source files present in `src/main/java/com/example/`
- [ ] Spring XML config: `src/main/resources/applicationContext.xml`
- [ ] Run build script from project directory

---

## 🎯 Next Steps After Running

1. **Review Output** - Understand Spring DI in action
2. **Modify Values** - Edit beans in config and re-run
3. **Explore Code** - Study the implementation
4. **Push to GitHub** - Use `GITHUB_PUSH_GUIDE.md`

---

## 📞 Need Help?

- See: `QUICK_START.md` - Step-by-step guide
- See: `ALTERNATIVE_BUILD_METHODS.md` - Other build options
- See: `SPRING_DI_README.md` - Spring DI concepts

---

**Project Status**: ✅ Ready once JARs are downloaded
