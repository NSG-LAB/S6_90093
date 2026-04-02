# Hibernate CRUD Example

This project demonstrates basic CRUD operations using Hibernate ORM with MySQL.

## Structure
- `src/main/java/com/example/Student.java`: Entity class
- `src/main/java/com/example/MainApp.java`: Main application
- `src/main/resources/hibernate.cfg.xml`: Hibernate configuration
- `pom.xml`: Maven dependencies

## Prerequisites
- Java 17+
- Maven
- MySQL running with a database named `test` and user/password `root`/`root`

## How to Run
1. Run `mvn compile` to build the project.
2. Run `MainApp.java` from your IDE or with Maven exec plugin.

## Notes
- Update `hibernate.cfg.xml` if your MySQL credentials or database name differ.
