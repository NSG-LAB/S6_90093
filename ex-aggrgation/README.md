# ex-aggrtion

A Java console application demonstrating Hibernate CRUD, aggregation, and sorting with MySQL.

## Features
- Employee entity (JPA)
- Full CRUD operations using Hibernate
- Aggregate functions (COUNT, AVG)
- Sorting by salary

## Prerequisites
- Java 17+
- Maven
- MySQL (running on localhost:3306, database `test`, user `root`, password `root`)

## How to Run
1. Clone/download this project.
2. Ensure MySQL is running and the `test` database exists.
3. Build the project:
   ```
   mvn clean compile
   ```
4. Run the main class:
   ```
   mvn exec:java -Dexec.mainClass="com.example.exaggrtion.FullHQLExample"
   ```

## Configuration
- Edit `src/main/resources/hibernate.cfg.xml` for DB settings if needed.

## Notes
- The application will create/update the `employee` table automatically.
- Sample data is inserted and manipulated on each run.
