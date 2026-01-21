# Spring Dependency Injection - Constructor & Setter Injection

## Project Overview
This project demonstrates Spring Framework's Dependency Injection (DI) capabilities using both **Constructor Injection** and **Setter Injection** with XML and Annotation-based configurations.

The system automates the management of student information, injecting details such as studentId, name, course, and academic year into objects without manually assigning values.

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           ├── Student.java                  # POJO class
│   │           ├── ApplicationConfig.java        # Java Configuration class
│   │           ├── MainXML.java                  # XML-based DI demo
│   │           └── MainAnnotation.java           # Annotation-based DI demo
│   └── resources/
│       ├── applicationContext.xml               # Spring XML Configuration
│       └── hibernate.cfg.xml
├── pom.xml                                       # Maven configuration with Spring dependencies
└── README.md
```

## Key Components

### 1. Student POJO Class (`Student.java`)
- **Fields**: `studentId`, `name`, `course`, `year`
- **Constructors**:
  - Default no-arg constructor (for setter injection)
  - Parameterized constructor accepting all fields (for constructor injection)
- **Methods**: Getter and setter methods for all fields
- **toString()**: For easy bean printing

### 2. XML Configuration (`applicationContext.xml`)
Defines two Spring beans:
- `studentConstructor`: Uses constructor-arg for Constructor Injection
- `studentSetter`: Uses property elements for Setter Injection

### 3. Java Configuration (`ApplicationConfig.java`)
Uses `@Configuration` and `@Bean` annotations to define:
- `studentConstructorAnnotation()`: Creates bean using constructor
- `studentSetterAnnotation()`: Creates bean using setter methods

### 4. Main Classes
- **MainXML.java**: Loads Spring container from XML and demonstrates DI
- **MainAnnotation.java**: Loads Spring container from Java config and demonstrates DI

## Dependencies
The project includes the following Spring Framework dependencies (version 6.1.0):
- `spring-core`: Core Spring functionality
- `spring-beans`: Bean factory and bean support
- `spring-context`: ApplicationContext for bean management

## Dependency Injection Methods

### Constructor Injection (XML)
```xml
<bean id="studentConstructor" class="com.example.Student">
    <constructor-arg name="studentId" value="101"/>
    <constructor-arg name="name" value="John Doe"/>
    <constructor-arg name="course" value="Computer Science"/>
    <constructor-arg name="year" value="2"/>
</bean>
```

### Setter Injection (XML)
```xml
<bean id="studentSetter" class="com.example.Student">
    <property name="studentId" value="102"/>
    <property name="name" value="Jane Smith"/>
    <property name="course" value="Information Technology"/>
    <property name="year" value="3"/>
</bean>
```

### Constructor Injection (Annotation)
```java
@Bean(name = "studentConstructorAnnotation")
public Student studentConstructorAnnotation() {
    return new Student(201, "Alice Johnson", "Electronics", 1);
}
```

### Setter Injection (Annotation)
```java
@Bean(name = "studentSetterAnnotation")
public Student studentSetterAnnotation() {
    Student student = new Student();
    student.setStudentId(202);
    student.setName("Bob Wilson");
    student.setCourse("Mechanical Engineering");
    student.setYear(4);
    return student;
}
```

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Spring Framework 6.1.0+

### Build the Project
```bash
mvn clean compile
```

### Run XML-based DI Demo
```bash
mvn exec:java -Dexec.mainClass="com.example.MainXML"
```

**Expected Output**:
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

### Run Annotation-based DI Demo
```bash
mvn exec:java -Dexec.mainClass="com.example.MainAnnotation"
```

**Expected Output**:
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

## Learning Outcomes
1. **Constructor Injection**: Understand how Spring injects dependencies through constructors
2. **Setter Injection**: Learn how Spring uses setter methods for dependency injection
3. **XML Configuration**: Configure Spring beans using XML files
4. **Annotation-based Configuration**: Use Java annotations for Spring configuration
5. **IoC Container**: Understand how Spring's ApplicationContext manages beans
6. **Bean Lifecycle**: Learn about bean creation and retrieval from Spring container

## Advantages and Disadvantages

### Constructor Injection
**Advantages**:
- Immutable objects
- Mandatory dependencies are clear
- Better for testing

**Disadvantages**:
- Constructor parameters can become numerous
- Not suitable for optional dependencies

### Setter Injection
**Advantages**:
- Flexible for optional dependencies
- Can be used after object creation
- Cleaner constructors

**Disadvantages**:
- Objects can be in incomplete state
- Optional dependencies are not explicit

## GitHub Repository
This project has been pushed to GitHub for version control and collaboration.

## Author
Created as part of Spring Framework DI and IoC training and demonstration.

## References
- [Spring Framework Documentation](https://spring.io/projects/spring-framework)
- [Spring DI and IoC Concepts](https://docs.spring.io/spring-framework/reference/core/beans.html)
- [Constructor vs Setter Injection](https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-constructor-injection.html)
