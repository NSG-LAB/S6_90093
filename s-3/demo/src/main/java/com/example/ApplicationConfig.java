package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Configuration class for Annotation-based Dependency Injection
 * Uses @Configuration and @Bean annotations instead of XML configuration
 */
@Configuration
public class ApplicationConfig {

    /**
     * Constructor Injection Bean
     */
    @Bean(name = "studentConstructorAnnotation")
    public Student studentConstructorAnnotation() {
        return new Student(201, "Alice Johnson", "Electronics", 1);
    }

    /**
     * Setter Injection Bean
     */
    @Bean(name = "studentSetterAnnotation")
    public Student studentSetterAnnotation() {
        Student student = new Student();
        student.setStudentId(202);
        student.setName("Bob Wilson");
        student.setCourse("Mechanical Engineering");
        student.setYear(4);
        return student;
    }
}
