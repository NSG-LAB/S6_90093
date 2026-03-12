package com.example;

import com.example.model.StudentDemo;

/**
 * Demonstration of Spring Autowiring concept without requiring Spring Framework
 * This simulates what Spring does internally with @Autowired annotation
 */
public class AutowiringDemo {
    public static void main(String[] args) {
        System.out.println("========== Spring Autowiring Demo ==========\n");
        System.out.println("This demonstration shows how Spring @Autowired annotation works\n");

        // Simulate what Spring does with @Autowired
        // In real Spring: Spring would detect StudentDemo and Certification classes
        // Spring would create instances and inject them automatically
        
        StudentDemo student = new StudentDemo();
        
        System.out.println("Student Information:");
        System.out.println("---------------------");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());
        
        System.out.println("\nAutowired Certification Information:");
        System.out.println("-----------------------------------");
        if (student.getCertification() != null) {
            System.out.println("Certification ID: " + student.getCertification().getId());
            System.out.println("Certification Name: " + student.getCertification().getName());
            System.out.println("Date of Completion: " + student.getCertification().getDateOfCompletion());
        } else {
            System.out.println("No certification assigned!");
        }

        System.out.println("\nComplete Student Object:");
        System.out.println("------------------------");
        System.out.println(student.toString());

        System.out.println("\n========== Demonstration Complete ==========");
        System.out.println("\nHow This Relates to Spring @Autowired:");
        System.out.println("----------------------------------------");
        System.out.println("1. StudentDemo class has a Certification field");
        System.out.println("2. Spring detects @Component annotation on both classes");
        System.out.println("3. Spring creates instances of both classes");
        System.out.println("4. Spring sees @Autowired on the certification field");
        System.out.println("5. Spring automatically injects the Certification bean");
        System.out.println("6. Result: Student object has Certification automatically!");
        System.out.println("\nTo run the full Spring version:");
        System.out.println("Install Maven and run: mvn exec:java -Dexec.mainClass=\"com.example.XMLConfigMain\"");
    }
}
