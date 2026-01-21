# Spring Autowiring Demo - Quick Reference

## Quick Start

### 1. Build the Project
```bash
mvn clean compile
```

### 2. Run XML Configuration Demo
```bash
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
```

### 3. Run Java Configuration Demo
```bash
mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
```

### 4. Build Executable JAR
```bash
mvn clean package
java -cp target/spring-autowiring-demo-1.0.0.jar com.example.XMLConfigMain
```

## Key Files

| File | Purpose |
|------|---------|
| `src/main/java/com/example/model/Certification.java` | Bean class with @Component |
| `src/main/java/com/example/model/Student.java` | Bean class with @Component and @Autowired |
| `src/main/java/com/example/config/AppConfig.java` | Java-based Spring configuration |
| `src/main/java/com/example/XMLConfigMain.java` | Entry point using XML config |
| `src/main/java/com/example/AnnotationConfigMain.java` | Entry point using Java config |
| `src/main/resources/applicationContext.xml` | XML Spring configuration |
| `pom.xml` | Maven build configuration |

## @Autowired Injection Methods

### Field Injection (Demonstrated in this project)
```java
@Component
public class Student {
    @Autowired
    private Certification certification;
}
```

### Constructor Injection
```java
@Component
public class Student {
    private Certification certification;
    
    @Autowired
    public Student(Certification certification) {
        this.certification = certification;
    }
}
```

### Setter Injection
```java
@Component
public class Student {
    private Certification certification;
    
    @Autowired
    public void setCertification(Certification certification) {
        this.certification = certification;
    }
}
```

## Spring Bean Lifecycle

1. **Instantiation**: Spring creates instances of @Component beans
2. **Population**: Spring sets property values
3. **Initialization**: Spring calls initialization methods
4. **Injection**: Spring injects dependencies using @Autowired
5. **Ready**: Bean is ready for use
6. **Destruction**: Spring destroys beans when context closes

## Useful Maven Commands

```bash
# Clean build artifacts
mvn clean

# Compile source code
mvn compile

# Run tests
mvn test

# Create JAR package
mvn package

# Install to local repository
mvn install

# Run specific class
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"

# View dependency tree
mvn dependency:tree
```

## Common Issues and Solutions

| Issue | Solution |
|-------|----------|
| No beans found | Check @ComponentScan basePackages |
| Autowired field is null | Ensure bean is created by Spring, not manually |
| Bean not detected | Verify @Component annotation is present |
| XML file not found | Ensure applicationContext.xml is in src/main/resources |

## Project Dependencies

- **Spring Framework 5.3.23**
  - spring-core
  - spring-context
  - spring-beans
- **SLF4J 1.7.36** (for logging)
  - slf4j-api
  - slf4j-simple

## IDE Setup

### IntelliJ IDEA
1. Open project root directory
2. Maven should auto-detect pom.xml
3. Run configurations: Run → Edit Configurations → Add Maven

### Eclipse
1. File → Import → Existing Maven Projects
2. Select project root
3. Run as → Maven build

### VS Code
1. Install Extension Pack for Java
2. Install Maven for Java extension
3. Run tasks from Task Explorer
