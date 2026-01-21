package com.example;

import com.example.config.AppConfig;
import com.example.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class demonstrating Spring @Autowired annotation using Java annotation-based configuration.
 * 
 * This class shows how to:
 * 1. Load the Spring IoC container using Java configuration class (@Configuration)
 * 2. Retrieve the Student bean from the container
 * 3. Access the automatically injected Certification object
 * 4. Print all student details including certification information
 */
public class AnnotationConfigMain {
    public static void main(String[] args) {
        System.out.println("========== Spring Autowiring Demo using Java Configuration ==========\n");

        // Load the Spring IoC container using Java configuration class
        // The AppConfig class contains @ComponentScan directive for bean detection
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Retrieve the Student bean from the Spring IoC container
        Student student = context.getBean(Student.class);

        // The Certification object is automatically injected by Spring
        // due to @Autowired annotation in the Student class
        System.out.println("Student Information:");
        System.out.println("---------------------");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Gender: " + student.getGender());
        
        System.out.println("\nInjected Certification Information:");
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

        // Note: In a real scenario, you would set actual values to the student and certification
        // objects using setters. This demo shows the @Autowired injection mechanism.
    }
}
