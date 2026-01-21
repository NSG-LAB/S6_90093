package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class to demonstrate Annotation-based Spring Dependency Injection
 * Shows both Constructor Injection and Setter Injection using Java @Configuration
 */
public class MainAnnotation {

    public static void main(String[] args) {
        System.out.println("=== Spring Annotation-based Dependency Injection Demo ===\n");

        // Load Spring IoC container from Java Configuration class
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        // Retrieve Constructor Injection Bean
        System.out.println("--- Constructor Injection (Annotation Configuration) ---");
        Student studentConstructor = context.getBean("studentConstructorAnnotation", Student.class);
        System.out.println("Bean Retrieved: " + studentConstructor);
        System.out.println("Student ID: " + studentConstructor.getStudentId());
        System.out.println("Name: " + studentConstructor.getName());
        System.out.println("Course: " + studentConstructor.getCourse());
        System.out.println("Year: " + studentConstructor.getYear());

        System.out.println("\n--- Setter Injection (Annotation Configuration) ---");
        // Retrieve Setter Injection Bean
        Student studentSetter = context.getBean("studentSetterAnnotation", Student.class);
        System.out.println("Bean Retrieved: " + studentSetter);
        System.out.println("Student ID: " + studentSetter.getStudentId());
        System.out.println("Name: " + studentSetter.getName());
        System.out.println("Course: " + studentSetter.getCourse());
        System.out.println("Year: " + studentSetter.getYear());

        // Close the context
        ((AnnotationConfigApplicationContext) context).close();
        System.out.println("\n=== Application Closed ===");
    }
}
