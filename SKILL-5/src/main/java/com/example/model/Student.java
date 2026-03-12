package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Student class representing a college student with their certification details.
 * The @Autowired annotation is used to inject the Certification dependency
 * automatically by Spring without using the new keyword.
 */
@Component
public class Student {
    private int id;
    private String name;
    private String gender;

    /**
     * The Certification object is automatically injected by Spring using @Autowired.
     * Spring retrieves the Certification bean from the IoC container and
     * assigns it to this field without manual instantiation.
     */
    @Autowired
    private Certification certification;

    /**
     * Default constructor for Spring to instantiate.
     */
    public Student() {
        this.id = 0;
        this.name = "Unknown";
        this.gender = "Not Specified";
        this.certification = null;
    }

    /**
     * Parameterized constructor (optional - can be used for manual creation).
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

    /**
     * Returns a string representation of the Student object including the injected Certification.
     */
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
