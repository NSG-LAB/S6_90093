package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Student class representing a student object.
 * Marked with @Component for Spring to auto-detect and create a bean.
 * Uses @Autowired to inject Certification object automatically.
 */
@Component
public class Student {
    private int id;
    private String name;
    private String gender;
    
    // Certification object injected by Spring using @Autowired
    @Autowired
    private Certification certification;

    /**
     * Default constructor.
     */
    public Student() {
        this.id = 101;
        this.name = "John Doe";
        this.gender = "Male";
    }

    /**
     * Parameterized constructor.
     */
    public Student(int id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    /**
     * Constructor with Certification injection.
     * Alternative approach for dependency injection via constructor.
     */
    public Student(int id, String name, String gender, Certification certification) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.certification = certification;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Certification getCertification() {
        return certification;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", certification=" + certification +
                '}';
    }
}
