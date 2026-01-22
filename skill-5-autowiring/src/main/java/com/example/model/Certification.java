package com.example.model;

import org.springframework.stereotype.Component;

/**
 * Certification class representing a certification object.
 * Marked with @Component for Spring to auto-detect and create a bean.
 */
@Component
public class Certification {
    private int id;
    private String name;
    private String dateOfCompletion;

    /**
     * Default constructor required by Spring for bean creation.
     */
    public Certification() {
        this.id = 1001;
        this.name = "Java Programming Certification";
        this.dateOfCompletion = "2024-12-15";
    }

    /**
     * Parameterized constructor for creating certification objects.
     */
    public Certification(int id, String name, String dateOfCompletion) {
        this.id = id;
        this.name = name;
        this.dateOfCompletion = dateOfCompletion;
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

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    @Override
    public String toString() {
        return "Certification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfCompletion='" + dateOfCompletion + '\'' +
                '}';
    }
}
