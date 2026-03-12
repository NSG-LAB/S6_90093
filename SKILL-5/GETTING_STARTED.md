# Spring Autowiring Demo - Getting Started Guide

Welcome to the Spring Autowiring Demo project! This guide will help you get started quickly.

## 📋 Before You Start

Ensure you have:
- ✅ Java 11 or higher installed
- ✅ Maven 3.6.0 or higher installed

Check by running:
```bash
java -version
mvn -version
```

If not installed, see [SETUP_GUIDE.md](SETUP_GUIDE.md)

---

## 🚀 Quick Start (5 Minutes)

### Step 1: Build the Project
```bash
# Windows
build.bat

# Linux/Mac
./build.sh

# Or directly with Maven
mvn clean compile
```

### Step 2: Run the XML Configuration Demo
```bash
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
```

### Step 3: Run the Java Configuration Demo
```bash
mvn exec:java -Dexec.mainClass="com.example.AnnotationConfigMain"
```

You should see output like:
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
Student{id=0, name='Unknown', gender='Not Specified', certification=Certification{id=0, name='Not Assigned', dateOfCompletion='Not Available'}}

========== Demonstration Complete ==========
```

✅ **Success!** You've successfully run the Spring Autowiring Demo!

---

## 📚 What Did You Just Run?

### What Happened Behind the Scenes:

1. **Spring Created Beans**
   - Spring detected classes marked with `@Component`
   - Created instances of `Certification` and `Student` classes
   - Stored them in the IoC container

2. **Spring Injected Dependencies**
   - Spring found `@Autowired` annotation on the `certification` field
   - Automatically retrieved the `Certification` bean from the container
   - Injected it into the `Student` bean

3. **Application Retrieved and Displayed**
   - Retrieved the `Student` bean from Spring container
   - Displayed student information
   - Displayed the automatically injected `Certification` information

### Key Concept: @Autowired
The `@Autowired` annotation tells Spring to automatically inject dependencies. Instead of:
```java
Student student = new Student();
student.setCertification(new Certification());  // Manual creation
```

Spring does it automatically:
```java
@Autowired
private Certification certification;  // Spring handles creation and injection
```

---

## 📖 Understanding the Code

### Three Main Components

#### 1. Certification Class
```java
@Component
public class Certification {
    private int id;
    private String name;
    private String dateOfCompletion;
    // getters, setters, toString()
}
```
- Marked with `@Component` so Spring detects it
- Spring automatically creates instances

#### 2. Student Class
```java
@Component
public class Student {
    private int id;
    private String name;
    private String gender;
    
    @Autowired
    private Certification certification;  // Automatically injected!
    // getters, setters, toString()
}
```
- Marked with `@Component` so Spring detects it
- Has `@Autowired` field that Spring automatically injects

#### 3. Configuration (Two Approaches)

**XML Configuration:**
```xml
<context:component-scan base-package="com.example.model"/>
```
- Tells Spring to scan the package for `@Component` beans

**Java Configuration:**
```java
@Configuration
@ComponentScan(basePackages = "com.example.model")
public class AppConfig {
}
```
- Same as XML but in Java code (modern approach)

---

## 🔍 Comparing XML vs Java Configuration

Both approaches achieve the same result but using different syntax:

| Aspect | XML | Java |
|--------|-----|------|
| File Location | `src/main/resources/applicationContext.xml` | `src/main/java/com/example/config/AppConfig.java` |
| Configuration | `<context:component-scan>` element | `@ComponentScan` annotation |
| Loading | `ClassPathXmlApplicationContext` | `AnnotationConfigApplicationContext` |
| Entry Point | XMLConfigMain.java | AnnotationConfigMain.java |
| Approach | Traditional | Modern |
| Type Safety | No | Yes |

---

## 🎯 How @Autowired Works (Step by Step)

1. **Component Scanning**: Spring scans packages and finds classes with `@Component`
2. **Bean Creation**: Spring creates instances of found classes
3. **Dependency Detection**: Spring analyzes classes for `@Autowired` fields
4. **Dependency Resolution**: Spring looks for matching beans in the container
5. **Injection**: Spring injects the found bean into the `@Autowired` field
6. **Ready for Use**: Application code can access the injected object

**Result**: The `Student` bean has its `Certification` field automatically filled by Spring!

---

## 🛠️ What to Try Next

### Easy Modifications

1. **Set Initial Values**
   - Modify the `XMLConfigMain.java` to set student values:
   ```java
   student.setId(101);
   student.setName("John Doe");
   student.setGender("Male");
   student.getCertification().setId(1);
   student.getCertification().setName("Java Certification");
   student.getCertification().setDateOfCompletion("2026-01-21");
   ```

2. **Add More Fields**
   - Add `email` field to Student
   - Add `provider` field to Certification
   - See how @Autowired still works

3. **Create More Classes**
   - Create an `Address` class with @Component
   - Add `@Autowired` field for Address in Student
   - See multiple dependency injections

### Intermediate Modifications

4. **Try Constructor Injection**
   - Replace field injection with constructor injection:
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

5. **Try Setter Injection**
   - Replace with setter injection:
   ```java
   @Autowired
   public void setCertification(Certification certification) {
       this.certification = certification;
   }
   ```

---

## 📖 Documentation Files

| File | Purpose |
|------|---------|
| [README.md](README.md) | Detailed project documentation |
| [INDEX.md](INDEX.md) | File organization and navigation |
| [QUICK_REFERENCE.md](QUICK_REFERENCE.md) | Commands and quick tips |
| [SETUP_GUIDE.md](SETUP_GUIDE.md) | Installation and troubleshooting |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | Project overview |
| [COMPLETION_REPORT.md](COMPLETION_REPORT.md) | Task completion details |

**Start with**: [README.md](README.md) for comprehensive understanding

---

## ❓ Common Questions

### Q: Why use Spring instead of creating objects with `new`?
**A**: Spring provides:
- Automatic dependency resolution
- Loose coupling between classes
- Easy testing with mocks
- Centralized configuration
- Lifecycle management

### Q: What if I have multiple Certification beans?
**A**: Use `@Qualifier` annotation:
```java
@Autowired
@Qualifier("certificationV1")
private Certification certification;
```

### Q: Can I use @Autowired on constructor parameters?
**A**: Yes! Constructor injection is actually recommended:
```java
@Autowired
public Student(Certification certification) {
    this.certification = certification;
}
```

### Q: What if the bean is not found?
**A**: You'll get `UnsatisfiedDependencyException`. Verify:
- Class has `@Component` annotation
- Package is included in component scan
- Class name is correct
- Spring context is loaded properly

### Q: Is XML or Java configuration better?
**A**: Both are valid. Java configuration is more modern and type-safe, but XML is familiar to legacy projects.

---

## 🚨 Troubleshooting

### Build Fails
```bash
# Check Java version
java -version  # Should be 11+

# Check Maven version
mvn -version   # Should be 3.6.0+

# Clean and rebuild
mvn clean compile
```

### No Output
```bash
# Ensure correct class name
mvn exec:java -Dexec.mainClass="com.example.XMLConfigMain"
# Not: "XMLConfigMain" or "com.example.XMLConfigMain.java"
```

### Beans Not Found
- Check `@Component` annotation is present
- Verify package name in component scan matches actual package
- Ensure XML file is in `src/main/resources`

### More Help
See [SETUP_GUIDE.md](SETUP_GUIDE.md) for detailed troubleshooting

---

## ✅ Verification Checklist

- ✅ Java 11+ installed
- ✅ Maven installed and working
- ✅ Project builds without errors
- ✅ XML demo runs and shows output
- ✅ Java configuration demo runs and shows output
- ✅ Both outputs are identical
- ✅ Student information is displayed
- ✅ Certification information is displayed

---

## 📞 Getting More Help

1. **Project Documentation**: Read [README.md](README.md)
2. **Quick Commands**: Check [QUICK_REFERENCE.md](QUICK_REFERENCE.md)
3. **Setup Issues**: See [SETUP_GUIDE.md](SETUP_GUIDE.md)
4. **Official Spring Docs**: [spring.io](https://spring.io/projects/spring-framework)

---

## 🎓 Learning Path

### Beginner
1. Run the demos
2. Read this guide
3. Study the source code

### Intermediate  
1. Modify classes to add fields
2. Set actual values after bean creation
3. Try different injection methods

### Advanced
1. Create multiple beans and use @Qualifier
2. Implement lifecycle callbacks
3. Build complete application layers

---

## 🎉 You're Ready!

You now understand Spring's @Autowired dependency injection! 

**Next Actions**:
1. Experiment with the code
2. Modify classes and test
3. Create new components
4. Build real Spring applications

**Happy Learning!** 🚀

---

**Quick Links**:
- [Full Documentation](README.md)
- [Project Index](INDEX.md)  
- [Setup Guide](SETUP_GUIDE.md)
- [Quick Reference](QUICK_REFERENCE.md)

---

*Last Updated: January 2026*  
*Spring Framework: 5.3.23*  
*Java: 11+*
