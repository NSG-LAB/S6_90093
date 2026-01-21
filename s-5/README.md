# Spring Autowiring Demo using @Autowired

## Project Overview

This project demonstrates the use of Spring's `@Autowired` annotation for dependency injection. It showcases how Spring automatically injects dependencies into beans without requiring manual instantiation using the `new` keyword.

### Key Concepts Demonstrated

1. **@Component Annotation**: Marks classes as Spring-managed components
2. **@Autowired Annotation**: Enables automatic dependency injection
3. **Component Scanning**: Automatic detection of Spring beans
4. **IoC Container**: Spring's Inversion of Control container management
5. **Two Configuration Approaches**: Both XML and Java annotation-based configuration

## Project Structure

```
s-5/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           ├── model/
│   │   │           │   ├── Certification.java    (Bean with @Component)
│   │   │           │   └── Student.java          (Bean with @Component and @Autowired)
│   │   │           ├── config/
│   │   │           │   └── AppConfig.java        (Java Configuration)
│   │   │           ├── XMLConfigMain.java        (Entry point for XML config)
│   │   │           └── AnnotationConfigMain.java (Entry point for Java config)
│   │   └── resources/
│   │       └── applicationContext.xml            (XML Configuration)
└── pom.xml                                        (Maven Configuration)
```

## Classes and Their Roles

### 1. Certification Class (`com.example.model.Certification`)

**Annotations Used**: `@Component`

**Purpose**: Represents a student's certification details

**Fields**:
- `id` (int): Unique identifier for certification
- `name` (String): Name of the certification
- `dateOfCompletion` (String): Date when certification was completed

**Key Points**:
- Marked with `@Component` to enable Spring detection
- Spring will automatically instantiate and manage this bean

### 2. Student Class (`com.example.model.Student`)

**Annotations Used**: `@Component`, `@Autowired`

**Purpose**: Represents a student with certification information

**Fields**:
- `id` (int): Student ID
- `name` (String): Student name
- `gender` (String): Student gender
- `certification` (Certification): **Automatically injected by Spring**

**Key Points**:
- `@Component` marks it as a Spring-managed bean
- `@Autowired` on the `certification` field enables automatic injection
- Spring retrieves the `Certification` bean from IoC container and assigns it

### 3. AppConfig Class (`com.example.config.AppConfig`)

**Annotations Used**: `@Configuration`, `@ComponentScan`

**Purpose**: Java-based configuration for Spring

**Key Points**:
- `@Configuration` indicates this class contains Spring configuration
- `@ComponentScan(basePackages = "com.example.model")` tells Spring where to scan for beans
- Replaces XML configuration with type-safe Java code

### 4. XMLConfigMain Class

**Purpose**: Entry point demonstrating XML-based configuration

**Execution Flow**:
1. Loads Spring IoC container using `ClassPathXmlApplicationContext("applicationContext.xml")`
2. Retrieves `Student` bean from container
3. Displays student details including the injected `Certification` object

### 5. AnnotationConfigMain Class

**Purpose**: Entry point demonstrating Java annotation-based configuration

**Execution Flow**:
1. Loads Spring IoC container using `AnnotationConfigApplicationContext(AppConfig.class)`
2. Retrieves `Student` bean from container
3. Displays student details including the injected `Certification` object

## Configuration Methods

### Method 1: XML Configuration (`applicationContext.xml`)

```xml
<context:component-scan base-package="com.example.model"/>
```

- Traditional Spring configuration approach
- Component scanning directive enables auto-detection of `@Component` beans
- `@Autowired` annotation works with this configuration

### Method 2: Java Annotation Configuration (`AppConfig.java`)

```java
@Configuration
@ComponentScan(basePackages = "com.example.model")
public class AppConfig {
}
```

- Modern Spring configuration approach
- Type-safe and IDE-friendly
- Achieves the same result as XML configuration

## How @Autowired Works

1. **Bean Detection**: Spring scans the specified packages and finds classes annotated with `@Component`
2. **Bean Creation**: Spring instantiates `Certification` and `Student` beans
3. **Dependency Resolution**: Spring identifies that `Student` has an `@Autowired` field of type `Certification`
4. **Injection**: Spring retrieves the `Certification` bean from IoC container and injects it into `Student`
5. **Result**: The `Student` object has a fully initialized `Certification` object without manual instantiation

## Building the Project

### Using Maven

```bash
# Clean and compile the project
mvn clean compile

# Package the project
mvn package

# Run specific main class
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
```

### Direct Java Execution (after building with Maven)

```bash
mvn clean package
java -cp target/spring-autowiring-demo-1.0.0.jar com.example.XMLConfigMain
java -cp target/spring-autowiring-demo-1.0.0.jar com.example.AnnotationConfigMain
```

## Expected Output

When you run either main class, you'll see:

```
========== Spring Autowiring Demo using XML Configuration ==========

Student Information:
---------------------
ID: 0
Name: Unknown
Gender: Not Specified

Injected Certification Information:
-----------------------------------
Certification ID: 0
Certification Name: Not Assigned
Date of Completion: Not Available

Complete Student Object:
------------------------
Student{id=0, name='Unknown', gender='Not Specified', certification=Certification{id=0, name='Not Assigned', dateOfCompletion='Not Available'}}

========== Demonstration Complete ==========
```

**Important Note**: The values shown are defaults because we're demonstrating the injection mechanism. In a real application, you would:
1. Retrieve the Student bean from the container
2. Use setters to set actual values
3. Then display the information

## Key Learning Points

1. **@Component**: Marks a class as a Spring component for auto-detection
2. **@Autowired**: Instructs Spring to automatically inject the dependency
3. **Component Scanning**: Tells Spring where to look for components
4. **Two Configuration Options**:
   - XML-based: Traditional, verbose, but familiar
   - Java annotation-based: Modern, type-safe, less boilerplate
5. **IoC Container**: Manages all bean creation and injection
6. **No Manual Instantiation**: Spring handles object creation and injection

## Extensions and Variations

### Using @Autowired with Constructor Injection

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

### Using @Autowired with Setter Injection

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

### Using @Qualifier for Multiple Beans

```java
@Autowired
@Qualifier("certification")
private Certification certification;
```

## Troubleshooting

1. **NoSuchBeanDefinitionException**: Ensure component scanning includes the package where your beans are located
2. **UnsatisfiedDependencyException**: Check that all required beans are marked with `@Component` or equivalent
3. **Null Pointer Exception on Autowired Field**: Verify that Spring is managing the containing bean (it should have `@Component`)

## Summary

This project demonstrates Spring's powerful dependency injection capability through the `@Autowired` annotation. It showcases how Spring reduces boilerplate code by automatically managing object creation and injection, making applications more maintainable and testable.
