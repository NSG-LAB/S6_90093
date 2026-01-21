package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class to demonstrate XML-based Spring Dependency Injection
 * Shows both Constructor Injection and Setter Injection using XML configuration
 */
public class MainXML {

    public static void main(String[] args) {
        System.out.println("=== Spring XML-based Dependency Injection Demo ===\n");

        // Load Spring IoC container from XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve Constructor Injection Bean
        System.out.println("--- Constructor Injection (XML Configuration) ---");
        Student studentConstructor = (Student) context.getBean("studentConstructor");
        System.out.println("Bean Retrieved: " + studentConstructor);
        System.out.println("Student ID: " + studentConstructor.getStudentId());
        System.out.println("Name: " + studentConstructor.getName());
        System.out.println("Course: " + studentConstructor.getCourse());
        System.out.println("Year: " + studentConstructor.getYear());

        System.out.println("\n--- Setter Injection (XML Configuration) ---");
        // Retrieve Setter Injection Bean
        Student studentSetter = (Student) context.getBean("studentSetter");
        System.out.println("Bean Retrieved: " + studentSetter);
        System.out.println("Student ID: " + studentSetter.getStudentId());
        System.out.println("Name: " + studentSetter.getName());
        System.out.println("Course: " + studentSetter.getCourse());
        System.out.println("Year: " + studentSetter.getYear());

        // Close the context
        ((ClassPathXmlApplicationContext) context).close();
        System.out.println("\n=== Application Closed ===");
    }
}
