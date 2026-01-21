# Spring Autowiring Demo - Setup Guide

## Prerequisites

Before running this project, ensure you have the following installed:

### 1. Java Development Kit (JDK) 11 or higher
```bash
# Check Java version
java -version

# Expected output: openjdk version "11" or higher
```

**Download**: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

### 2. Maven 3.6.0 or higher
```bash
# Check Maven version
mvn -version

# Expected output: Apache Maven 3.6.0 or higher
```

**Download**: [Apache Maven](https://maven.apache.org/download.cgi)

### 3. Git (Optional, for version control)
```bash
# Check Git version
git --version
```

**Download**: [Git](https://git-scm.com/)

## Installation Steps

### Windows Users

#### Step 1: Install JDK
1. Download JDK 11 or higher from Oracle or OpenJDK
2. Run the installer
3. Add JAVA_HOME to environment variables:
   - Right-click "This PC" → Properties
   - Click "Advanced system settings"
   - Click "Environment Variables"
   - Click "New" under System variables
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11` (adjust to your installation path)
4. Add Java to PATH:
   - Edit the PATH variable
   - Add `%JAVA_HOME%\bin`

#### Step 2: Install Maven
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to a folder (e.g., `C:\apache-maven`)
3. Add Maven to environment variables:
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\apache-maven` (your extraction path)
4. Add Maven to PATH:
   - Edit the PATH variable
   - Add `%MAVEN_HOME%\bin`

#### Step 3: Verify Installation
```cmd
java -version
mvn -version
```

### Linux/Mac Users

#### Using Homebrew (Mac)
```bash
# Install Java
brew install openjdk@11

# Install Maven
brew install maven

# Verify installation
java -version
mvn -version
```

#### Using apt (Ubuntu/Debian)
```bash
# Install Java
sudo apt-get install openjdk-11-jdk

# Install Maven
sudo apt-get install maven

# Verify installation
java -version
mvn -version
```

#### Manual Installation
1. Download JDK and Maven
2. Extract to appropriate directories
3. Add to PATH in `~/.bashrc` or `~/.zshrc`:
   ```bash
   export JAVA_HOME=/path/to/jdk
   export MAVEN_HOME=/path/to/maven
   export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
   ```

## Project Setup

### 1. Navigate to Project Directory
```bash
cd s-5
```

### 2. Verify pom.xml
```bash
cat pom.xml
```

Ensure it contains Spring dependencies (Spring 5.3.23)

### 3. Build the Project
```bash
# Windows
build.bat
# or
mvn clean compile

# Linux/Mac
./build.sh
# or
mvn clean compile
```

### 4. Run the Project

#### Option A: Using Maven
```bash
# Run XML Configuration Demo
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"

# Run Java Configuration Demo
mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
```

#### Option B: Using Provided Scripts (Windows)
```cmd
# XML Configuration Demo
run-xml-demo.bat

# Java Configuration Demo
run-annotation-demo.bat
```

#### Option C: Using PowerShell (Windows)
```powershell
# Build and run XML demo
.\build.ps1 -action run-xml

# Build and run Java config demo
.\build.ps1 -action run-annotation
```

## Troubleshooting

### Maven Not Found
**Problem**: `'mvn' is not recognized as an internal or external command`

**Solution**:
1. Ensure MAVEN_HOME is set
2. Ensure %MAVEN_HOME%\bin is in PATH
3. Restart command prompt/terminal
4. Verify: `mvn -version`

### Java Not Found
**Problem**: `'java' is not recognized as an internal or external command`

**Solution**:
1. Ensure JAVA_HOME is set
2. Ensure %JAVA_HOME%\bin is in PATH
3. Restart command prompt/terminal
4. Verify: `java -version`

### Maven Build Fails
**Problem**: `[ERROR] COMPILATION ERROR`

**Solution**:
1. Ensure JDK 11+ is installed: `java -version`
2. Ensure pom.xml has correct Spring version
3. Clean and rebuild: `mvn clean compile`
4. Check internet connection (Maven downloads dependencies)

### Spring Context Not Loading
**Problem**: Application runs but beans are not created

**Solution**:
1. Verify @Component annotations are present
2. Verify component scan package matches class package
3. Check applicationContext.xml is in src/main/resources
4. Verify @Configuration and @ComponentScan in AppConfig.java

### Autowired Field is Null
**Problem**: Certification object is null when accessed

**Solution**:
1. Ensure Certification class has @Component
2. Ensure Student class is managed by Spring (has @Component)
3. Verify @Autowired is on the certification field
4. Check that beans are retrieved from ApplicationContext, not manually instantiated

## IDE Setup

### IntelliJ IDEA
1. Open project root (s-5 directory)
2. IDEA auto-detects pom.xml
3. Wait for Maven to download dependencies
4. Right-click on XMLConfigMain.java → Run
5. Or Run → Edit Configurations → Add Maven run configuration

### Eclipse
1. File → Import → Existing Maven Projects
2. Select s-5 directory
3. Click Finish
4. Wait for Maven to download dependencies
5. Right-click on XMLConfigMain.java → Run As → Java Application

### VS Code
1. Install Extension Pack for Java
2. Install Maven for Java extension
3. Open project root (s-5 directory)
4. Run tasks from Terminal → Run Task
5. Or use Command Palette (Ctrl+Shift+P) → Maven: Execute Commands

## First Run Checklist

- [ ] Java 11+ installed and verified
- [ ] Maven 3.6.0+ installed and verified
- [ ] Project directory accessible
- [ ] pom.xml present and readable
- [ ] Source files compiled without errors
- [ ] XMLConfigMain runs successfully
- [ ] AnnotationConfigMain runs successfully
- [ ] Output shows Student and Certification objects
- [ ] Documentation reviewed

## Next Steps

1. **Understand the Code**
   - Read README.md
   - Study Student.java and Certification.java
   - Compare AppConfig.java with applicationContext.xml

2. **Experiment**
   - Modify Student and Certification classes
   - Add more fields and see how @Autowired handles them
   - Try different injection methods (constructor, setter)

3. **Advanced Topics**
   - Create multiple Certification beans and use @Qualifier
   - Implement InitializingBean or @PostConstruct
   - Explore @Scope annotations
   - Add lifecycle logging with @PreDestroy

4. **Real-World Application**
   - Create more complex object graphs
   - Use @Autowired with interfaces
   - Implement services and repositories
   - Add database connectivity

## Getting Help

1. Check [README.md](README.md) for detailed documentation
2. Review [QUICK_REFERENCE.md](QUICK_REFERENCE.md) for commands
3. See [INDEX.md](INDEX.md) for project file guide
4. Check official [Spring Framework Documentation](https://spring.io/projects/spring-framework)

## Additional Resources

- [Spring Framework Official Documentation](https://spring.io/projects/spring-framework)
- [Spring @Autowired Annotation](https://spring.io/blog/2010/11/23/spring-3-1-m1-introducing-the-spring-component-index)
- [Maven Getting Started Guide](https://maven.apache.org/guides/getting-started/)
- [Java Documentation](https://docs.oracle.com/en/java/)

---

**Last Updated**: January 2026
**Spring Version**: 5.3.23
**Java Version**: 11+
**Maven Version**: 3.6.0+
