# Spring Autowiring Demo - Skill 5

## Project Overview

This project demonstrates **Spring Dependency Injection using the @Autowired annotation** in a college application that maintains student information along with their certification details.

### Key Concepts

- **@Component**: Annotation to mark classes as Spring beans (auto-detected by component scanning)
- **@Autowired**: Annotation to inject dependencies automatically without using the `new` keyword
- **@Configuration**: Marks a class as a Spring configuration class
- **@ComponentScan**: Enables Spring to scan for @Component annotations
- **IoC Container**: Spring manages object creation and lifecycle

## Project Structure

```
skill-5-autowiring/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           ├── config/
│       │           │   └── AppConfig.java          (Java-based configuration)
│       │           ├── model/
│       │           │   ├── Certification.java      (@Component bean)
│       │           │   └── Student.java            (@Component bean with @Autowired)
│       │           ├── AnnotationConfigMain.java   (Annotation config demo)
│       │           └── XMLConfigMain.java          (XML config demo)
│       └── resources/
│           └── applicationContext.xml              (Spring XML configuration)
├── lib/                                             (Spring JAR files)
├── pom.xml                                          (Maven configuration)
├── run-demo.bat                                     (Windows batch script)
├── run-demo.ps1                                     (Windows PowerShell script)
├── build.sh                                         (Linux/Mac build script)
└── README.md
```

## Classes and Their Roles

### 1. Certification Class (`com.example.model.Certification`)

```java
@Component
public class Certification {
    private int id;
    private String name;
    private String dateOfCompletion;
    // getters, setters, toString()
}
```

**Purpose**: Represents a certification with id, name, and completion date.

**@Component**: Makes Spring auto-detect and create a bean for this class.

### 2. Student Class (`com.example.model.Student`)

```java
@Component
public class Student {
    private int id;
    private String name;
    private String gender;
    
    @Autowired
    private Certification certification;  // Automatically injected by Spring
    // getters, setters, toString()
}
```

**Purpose**: Represents a student with id, name, gender, and a Certification object.

**@Autowired**: Spring automatically injects a Certification bean instance into this field, eliminating the need to write:
```java
this.certification = new Certification();  // NOT NEEDED!
```

### 3. AppConfig Class (`com.example.config.AppConfig`)

```java
@Configuration
@ComponentScan(basePackages = "com.example.model")
public class AppConfig {
    // No bean definitions needed - annotation-based configuration handles everything
}
```

**Purpose**: Java-based Spring configuration.

- **@Configuration**: Marks this as a Spring configuration class
- **@ComponentScan**: Tells Spring to scan the `com.example.model` package for @Component annotations

### 4. AnnotationConfigMain Class (`com.example.AnnotationConfigMain`)

Demonstrates using Java-based configuration:
```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
Student student = context.getBean(Student.class);
```

### 5. XMLConfigMain Class (`com.example.XMLConfigMain`)

Demonstrates using XML-based configuration:
```java
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
Student student = context.getBean("student", Student.class);
```

## Spring Configuration Options

### Option 1: Annotation-Based Configuration (Modern Approach)

**Configuration file**: `AppConfig.java`

```java
@Configuration
@ComponentScan(basePackages = "com.example.model")
public class AppConfig {
}
```

**How it works**:
1. Spring scans the `com.example.model` package
2. Finds classes marked with `@Component` (Certification, Student)
3. Creates bean instances automatically
4. Resolves `@Autowired` dependencies by matching types

### Option 2: XML-Based Configuration (Traditional Approach)

**Configuration file**: `applicationContext.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="...">
    <context:component-scan base-package="com.example.model"/>
</beans>
```

**How it works**:
1. XML configuration enables component scanning for the same package
2. Spring finds @Component classes
3. Creates beans and resolves @Autowired dependencies

## Dependency Injection Flow

```
1. Spring loads configuration (AppConfig.java or applicationContext.xml)
2. Component scanning discovers @Component classes:
   - Certification.java → Creates Certification bean
   - Student.java → Creates Student bean
3. Spring processes @Autowired annotations:
   - Finds @Autowired in Student.certification field
   - Looks up a bean of type Certification
   - Automatically injects it
4. Student object now has Certification object without explicit creation
```

## Setup and Running

### Prerequisites

- Java 17 or higher
- Maven (optional, for dependency management)
- Spring 6.1.0 libraries

### Step 1: Download Spring JARs

You need 6 JAR files:

1. [spring-core-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-core/6.1.0/spring-core-6.1.0.jar)
2. [spring-beans-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-beans/6.1.0/spring-beans-6.1.0.jar)
3. [spring-context-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-context/6.1.0/spring-context-6.1.0.jar)
4. [spring-aop-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-aop/6.1.0/spring-aop-6.1.0.jar)
5. [spring-jcl-6.1.0.jar](https://repo.maven.apache.org/maven2/org/springframework/spring-jcl/6.1.0/spring-jcl-6.1.0.jar)
6. [commons-logging-1.2.jar](https://repo.maven.apache.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar)

Place all JAR files in the `lib/` folder.

### Step 2: Run the Project

#### Option A: Windows Batch Script
```batch
run-demo.bat
```

#### Option B: Windows PowerShell
```powershell
.\run-demo.ps1
```

#### Option C: Linux/Mac
```bash
chmod +x build.sh
./build.sh
```

#### Option D: Maven (if installed)
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
```

## Expected Output

The script runs both configurations:

### XML Configuration Demo Output
```
=== Spring XML-based Dependency Injection Demo ===

✓ Spring ApplicationContext created successfully

--- Student Information Retrieved from Spring Container ---
Student ID: 101
Name: John Doe
Gender: Male

--- Injected Certification Details ---
Certification ID: 1001
Certification Name: Java Programming Certification
Date of Completion: 2024-12-15

--- Full Student Object ---
Student{id=101, name='John Doe', gender='Male', certification=...}

✓ Autowiring demonstrated successfully!
=== Application Closed ===
```

### Annotation Configuration Demo Output
```
=== Spring Annotation-based Dependency Injection Demo ===

✓ Spring ApplicationContext created successfully

--- Student Information Retrieved from Spring Container ---
Student ID: 101
Name: John Doe
Gender: Male

--- Injected Certification Details ---
Certification ID: 1001
Certification Name: Java Programming Certification
Date of Completion: 2024-12-15

--- Full Student Object ---
Student{id=101, name='John Doe', gender='Male', certification=...}

✓ Autowiring demonstrated successfully!
=== Application Closed ===
```

## Key Learning Points

### What is @Autowired?

The `@Autowired` annotation tells Spring to automatically inject a dependency. Instead of:

```java
// Manual creation (WITHOUT @Autowired)
Student student = new Student();
Certification cert = new Certification();
student.setCertification(cert);
```

You write:

```java
// Automatic injection (WITH @Autowired)
@Component
public class Student {
    @Autowired
    private Certification certification;  // Spring injects this!
}
```

### Benefits of @Autowired

1. **Less boilerplate code**: No manual object creation
2. **Loose coupling**: Student doesn't depend on how Certification is created
3. **Testability**: Easy to mock Certification in unit tests
4. **Maintainability**: Changes to Certification don't affect Student creation code
5. **Flexibility**: Spring can inject different implementations

### How Spring Finds the Bean to Inject

Spring uses **type matching**:
1. Looks at the field type: `Certification`
2. Finds a bean that matches this type
3. Injects it automatically

If multiple beans of the same type exist, you can use `@Qualifier` to specify which one.

## File Descriptions

| File | Purpose |
|------|---------|
| `Certification.java` | Model class with @Component annotation |
| `Student.java` | Model class with @Autowired dependency |
| `AppConfig.java` | Java-based Spring configuration |
| `AnnotationConfigMain.java` | Runs demo using Java config |
| `XMLConfigMain.java` | Runs demo using XML config |
| `applicationContext.xml` | Spring XML configuration file |
| `pom.xml` | Maven build configuration |
| `run-demo.bat` | Windows batch build/run script |
| `run-demo.ps1` | Windows PowerShell script |
| `build.sh` | Linux/Mac bash script |

## Troubleshooting

### Issue: ClassNotFoundException
**Cause**: JAR files not in lib folder or classpath incorrect
**Solution**: Download Spring JARs and place in `lib/` folder

### Issue: NoClassDefFoundError: org/springframework/aop/TargetSource
**Cause**: Missing spring-aop JAR
**Solution**: Download spring-aop-6.1.0.jar and add to lib folder

### Issue: Bean not being found
**Cause**: @Component or @ComponentScan not properly configured
**Solution**: Ensure @Component is on Certification and Student, @ComponentScan points to correct package

## Next Steps

To extend this project:
1. Add more attributes to Student and Certification
2. Create multiple Certification objects and use `@Qualifier`
3. Implement constructor-based injection instead of field injection
4. Add setter-based injection alternative
5. Create a service layer and practice multi-level dependency injection

## References

- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Spring @Autowired Annotation](https://spring.io/guides/gs/autowired-dependencies/)
- [Component Scanning](https://spring.io/guides/gs/testing-web/)
