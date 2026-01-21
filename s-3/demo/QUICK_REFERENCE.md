# Spring DI - Quick Reference Guide

## 1. Student POJO Class Structure

```java
public class Student {
    private int studentId;
    private String name;
    private String course;
    private int year;
    
    // No-arg constructor (for setter injection)
    public Student() { }
    
    // All-args constructor (for constructor injection)
    public Student(int studentId, String name, String course, int year) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }
    
    // Getters and setters for all fields
}
```

---

## 2. XML Configuration Examples

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

### Using Constructor-arg by Index
```xml
<bean id="student" class="com.example.Student">
    <constructor-arg index="0" value="101"/>
    <constructor-arg index="1" value="John Doe"/>
    <constructor-arg index="2" value="Computer Science"/>
    <constructor-arg index="3" value="2"/>
</bean>
```

---

## 3. Annotation-based Configuration

### Java Configuration Class
```java
@Configuration
public class ApplicationConfig {
    
    @Bean(name = "studentConstructor")
    public Student studentConstructor() {
        return new Student(201, "Alice", "Electronics", 1);
    }
    
    @Bean(name = "studentSetter")
    public Student studentSetter() {
        Student student = new Student();
        student.setStudentId(202);
        student.setName("Bob");
        student.setCourse("Mechanical");
        student.setYear(4);
        return student;
    }
}
```

### Component Scanning Approach
```java
@Configuration
@ComponentScan("com.example")
public class AppConfig {
    // Spring will automatically scan and register beans
}

// In Student class:
@Component
public class Student {
    // class implementation
}
```

---

## 4. Loading Spring Context

### XML Configuration Loading
```java
ApplicationContext context = 
    new ClassPathXmlApplicationContext("applicationContext.xml");
```

### Annotation Configuration Loading
```java
ApplicationContext context = 
    new AnnotationConfigApplicationContext(ApplicationConfig.class);
```

### File System XML Loading
```java
ApplicationContext context = 
    new FileSystemXmlApplicationContext("path/to/applicationContext.xml");
```

---

## 5. Retrieving Beans

### Using getBean() with String
```java
Student student = (Student) context.getBean("studentConstructor");
```

### Using getBean() with Class
```java
Student student = context.getBean("studentConstructor", Student.class);
```

### Using getBean() without Name (when only one bean exists)
```java
Student student = context.getBean(Student.class);
```

---

## 6. Bean Properties

### Primitive Types
```xml
<property name="year" value="2"/>
<property name="studentId" value="101"/>
```

### String Types
```xml
<property name="name" value="John Doe"/>
<property name="course" value="Computer Science"/>
```

### With Annotation
```java
@Value("2")
private int year;

@Value("Computer Science")
private String course;
```

---

## 7. Constructor vs Setter Injection Comparison

| Aspect | Constructor Injection | Setter Injection |
|--------|----------------------|------------------|
| **Immutability** | Supports | Does not support |
| **Mandatory Dependencies** | Clear and enforced | Not explicit |
| **Partial Injection** | Not possible | Possible |
| **Circular Dependencies** | Can cause issues | Works fine |
| **Testing** | Easier | Requires care |
| **Code Clarity** | All dependencies visible | Need to check class |

---

## 8. Maven Commands

### Build Project
```bash
mvn clean compile
```

### Run Main Class
```bash
mvn exec:java -Dexec.mainClass="com.example.MainXML"
mvn exec:java -Dexec.mainClass="com.example.MainAnnotation"
```

### Create Package
```bash
mvn clean package
```

### Install Dependencies
```bash
mvn dependency:resolve
```

### View Dependency Tree
```bash
mvn dependency:tree
```

---

## 9. POM.xml Spring Dependencies

```xml
<!-- Spring Core -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>6.1.0</version>
</dependency>

<!-- Spring Beans -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-beans</artifactId>
    <version>6.1.0</version>
</dependency>

<!-- Spring Context -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.0</version>
</dependency>
```

---

## 10. Common Annotations

| Annotation | Purpose | Example |
|-----------|---------|---------|
| `@Configuration` | Marks class as config | `@Configuration class Config {}` |
| `@Bean` | Marks method as bean factory | `@Bean public Student student()` |
| `@Component` | Generic bean component | `@Component class Student` |
| `@Service` | Service layer component | `@Service class StudentService` |
| `@Repository` | Data layer component | `@Repository class StudentRepo` |
| `@Value` | Inject property values | `@Value("${prop}") String prop` |
| `@Autowired` | Dependency injection | `@Autowired Student student` |

---

## 11. File Structure Quick Reference

```
src/main/java/com/example/
├── Student.java                    # POJO with constructor & setters
├── ApplicationConfig.java          # @Configuration class
├── MainXML.java                   # Loads XML config
└── MainAnnotation.java            # Loads annotation config

src/main/resources/
└── applicationContext.xml          # Spring bean definitions
```

---

## 12. Debugging Tips

### Enable Debug Logging
```properties
# In log4j.properties or logback.xml
log4j.logger.org.springframework=DEBUG
```

### Print Bean Information
```java
String[] beanNames = context.getBeanDefinitionNames();
for (String name : beanNames) {
    System.out.println("Bean: " + name);
}
```

### Check Bean Properties
```java
Student student = context.getBean("studentConstructor", Student.class);
System.out.println("Student: " + student); // Uses toString()
```

---

## 13. Common Issues & Solutions

### Issue: Class Not Found
```
Solution: Ensure class path is correct and package structure matches
src/main/java/com/example/Student.java → package com.example;
```

### Issue: Bean Not Found
```
Solution: Check bean name in getBean() matches the bean id/name in config
```

### Issue: Constructor Argument Mismatch
```
Solution: Ensure constructor-arg values match constructor parameter types
```

### Issue: Null Pointer Exception on Setter
```
Solution: Default constructor may not set all properties, use constructor injection
```

---

## 14. Production Best Practices

1. **Use Constructor Injection for Required Dependencies**
   ```java
   public Student(int studentId, String name) {
       // Ensures required fields are set
   }
   ```

2. **Use Setter Injection for Optional Properties**
   ```java
   public void setCourse(String course) {
       // Can be set after object creation
   }
   ```

3. **Externalize Configuration**
   ```xml
   <property name="course" value="${student.course}"/>
   ```

4. **Use Property Placeholders**
   ```properties
   # application.properties
   student.name=John Doe
   student.course=Computer Science
   ```

5. **Separate Configuration from Code**
   - Keep XML/Java config separate from POJO classes
   - Use profiles for environment-specific configurations

---

## 15. Useful Links

- [Spring Framework Docs](https://spring.io/projects/spring-framework)
- [Spring Bean Reference](https://docs.spring.io/spring-framework/reference/core/beans.html)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Git Documentation](https://git-scm.com/doc)

---

## Quick Start Checklist

- [ ] Install Java 17+
- [ ] Install Maven 3.6+
- [ ] Clone/download project
- [ ] Run: `mvn clean compile`
- [ ] Run: `mvn exec:java -Dexec.mainClass="com.example.MainXML"`
- [ ] Run: `mvn exec:java -Dexec.mainClass="com.example.MainAnnotation"`
- [ ] Review output and understand bean injection
- [ ] Modify bean values and re-run
- [ ] Push to GitHub
