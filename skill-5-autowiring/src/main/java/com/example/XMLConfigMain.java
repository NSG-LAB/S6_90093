package com.example;

import com.example.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class demonstrating Spring Autowiring using XML-based Configuration.
 * Uses applicationContext.xml for component scanning configuration.
 */
public class XMLConfigMain {
    public static void main(String[] args) {
        System.out.println("=== Spring XML-based Dependency Injection Demo ===\n");

        try {
            // Create ApplicationContext using XML configuration file
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            System.out.println("✓ Spring ApplicationContext created successfully\n");

            // Retrieve the Student bean from the Spring container
            Student student = context.getBean("student", Student.class);
            
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
            System.out.println("  using XML-based component scanning and @Autowired annotation.\n");

            // Close the ApplicationContext
            if (context instanceof org.springframework.context.support.ClassPathXmlApplicationContext) {
                ((ClassPathXmlApplicationContext) context).close();
            }
            System.out.println("=== Application Closed ===");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
