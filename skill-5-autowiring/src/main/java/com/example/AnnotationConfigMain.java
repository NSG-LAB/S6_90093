package com.example;

import com.example.config.AppConfig;
import com.example.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class demonstrating Spring Autowiring using Annotation-based Configuration.
 * Uses @Configuration and @ComponentScan for automatic bean detection and dependency injection.
 */
public class AnnotationConfigMain {
    public static void main(String[] args) {
        System.out.println("=== Spring Annotation-based Dependency Injection Demo ===\n");

        try {
            // Create ApplicationContext using Java-based configuration
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            System.out.println("✓ Spring ApplicationContext created successfully\n");

            // Retrieve the Student bean from the Spring container
            Student student = context.getBean(Student.class);
            
            System.out.println("--- Student Information Retrieved from Spring Container ---");
            System.out.println("Student ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Gender: " + student.getGender());
            
            System.out.println("\n--- Injected Certification Details ---");
            System.out.println("Certification ID: " + student.getCertification().getId());
            System.out.println("Certification Name: " + student.getCertification().getName());
            System.out.println("Date of Completion: " + student.getCertification().getDateOfCompletion());
            
            System.out.println("\n--- Full Student Object ---");
            System.out.println(student);
            
            System.out.println("\n✓ Autowiring demonstrated successfully!");
            System.out.println("  The Certification object was automatically injected into Student");
            System.out.println("  using @Autowired annotation without using the 'new' keyword.\n");

            // Close the ApplicationContext
            ((AnnotationConfigApplicationContext) context).close();
            System.out.println("=== Application Closed ===");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
