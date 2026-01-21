# Spring DI Project - Setup & Installation Guide

## Prerequisites Installation

### 1. Install Java 17
- Download from: https://www.oracle.com/java/technologies/downloads/#java17
- Or use OpenJDK: https://adoptopenjdk.net/

**Verify Installation**:
```bash
java -version
```

### 2. Install Maven

#### Windows
1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract to a folder (e.g., `C:\Program Files\Maven`)
3. Add `M2_HOME` environment variable pointing to Maven folder
4. Add `%M2_HOME%\bin` to PATH

#### Linux/Mac
```bash
# Using brew (Mac)
brew install maven

# Using apt (Ubuntu/Debian)
sudo apt-get install maven
```

**Verify Installation**:
```bash
mvn -version
```

### 3. Configure Maven
Create or update `~/.m2/settings.xml` if needed for repository configuration.

## Project Setup

### Clone or Download Project
```bash
cd your-project-directory
git clone <repository-url>
cd demo
```

### Build Project
```bash
mvn clean compile
```

### Run Tests (if any)
```bash
mvn test
```

### Package the Project
```bash
mvn clean package
```

## Running the Application

### Execute Maven with Plugin Configuration
Add the following to `pom.xml` if exec plugin is not configured:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.0.0</version>
</plugin>
```

### Run XML-based Configuration
```bash
mvn exec:java -Dexec.mainClass="com.example.MainXML"
```

### Run Annotation-based Configuration
```bash
mvn exec:java -Dexec.mainClass="com.example.MainAnnotation"
```

### Alternative: Run with JAR
```bash
# Build JAR
mvn clean package -DskipTests

# Run JAR (choose one main class by modifying pom.xml)
java -cp target/demo-1.0-SNAPSHOT.jar:target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar com.example.MainXML
```

## Troubleshooting

### Issue: `mvn` command not found
- **Solution**: Ensure Maven is installed and PATH is correctly set
- **Windows**: Check `PATH` in Environment Variables
- **Linux/Mac**: Check `.bashrc` or `.zshrc` contains Maven path

### Issue: "Class not found" error
- **Solution**: Ensure all classes are in correct package structure
- Verify paths match: `src/main/java/com/example/`

### Issue: Spring dependency not resolved
- **Solution**: Check internet connection for Maven to download dependencies
- Clear Maven cache: `mvn clean dependency:resolve`

### Issue: Java version mismatch
- **Solution**: Ensure Java 17+ is installed
- Update `pom.xml` compiler source/target if different version is needed

## IDE Setup

### Eclipse
1. Right-click Project → Configure → Convert to Maven Project
2. Project → Properties → Java Build Path → JRE (select Java 17+)
3. Run Configuration → Maven Build → Goals: `exec:java -Dexec.mainClass="com.example.MainXML"`

### IntelliJ IDEA
1. Open Project → Module Settings → Project SDK (select Java 17+)
2. Run → Edit Configurations → Add new Maven configuration
3. Set Command line: `exec:java -Dexec.mainClass="com.example.MainXML"`

### VS Code
1. Install "Extension Pack for Java"
2. Open integrated terminal: `Ctrl + ~`
3. Run Maven commands directly

## Deployment to GitHub

### First Time Setup
```bash
# Initialize git (if not already done)
git init

# Add all files
git add .

# Create initial commit
git commit -m "Initial Spring DI project with constructor and setter injection"

# Add remote repository
git remote add origin https://github.com/yourusername/your-repo.git

# Push to GitHub
git branch -M main
git push -u origin main
```

### Subsequent Updates
```bash
# Check status
git status

# Add changes
git add .

# Commit with message
git commit -m "Description of changes"

# Push to remote
git push origin main
```

## Project Structure Details

```
demo/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/
│       │       ├── Student.java              # Student POJO
│       │       ├── ApplicationConfig.java    # Spring Configuration
│       │       ├── MainXML.java             # XML-based demo
│       │       └── MainAnnotation.java      # Annotation-based demo
│       └── resources/
│           └── applicationContext.xml        # Spring XML Config
├── pom.xml                                   # Maven configuration
├── .gitignore                               # Git ignore patterns
├── README.md                                # Original README
├── SPRING_DI_README.md                      # DI documentation
├── SETUP_GUIDE.md                           # This file
└── .git/                                    # Git repository

```

## Docker Support (Optional)

Create `Dockerfile` in project root:

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy Maven settings (if custom)
# COPY .m2/settings.xml ~/.m2/settings.xml

# Copy project files
COPY . .

# Build
RUN apt-get update && apt-get install -y maven
RUN mvn clean compile

# Run XML-based demo by default
CMD ["mvn", "exec:java", "-Dexec.mainClass=com.example.MainXML"]
```

Build and run with Docker:
```bash
docker build -t spring-di-demo .
docker run spring-di-demo
```

## Next Steps
1. Review the SPRING_DI_README.md for detailed project information
2. Explore the source code in `src/main/java/com/example/`
3. Modify the bean values in `applicationContext.xml` and `ApplicationConfig.java`
4. Create additional beans and explore Spring DI features
5. Add unit tests using JUnit and Spring Test framework

## Additional Resources
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Git Documentation](https://git-scm.com/doc)
