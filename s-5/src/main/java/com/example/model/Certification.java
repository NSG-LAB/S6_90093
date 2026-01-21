package com.example.model;

import org.springframework.stereotype.Component;

/**
 * Certification class representing a student's certification details.
 * This class is annotated with @Component to enable Spring to detect,
 * instantiate, and manage it as a bean in the IoC container.
 */
@Component
public class Certification {
    private int id;
    private String name;
    private String dateOfCompletion;

    /**
     * Default constructor for Spring to instantiate.
     */
    public Certification() {
        this.id = 0;
        this.name = "Not Assigned";
        this.dateOfCompletion = "Not Available";
    }

    /**
     * Parameterized constructor for creating certification instances.
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

    /**
     * Returns a string representation of the Certification object.
     */
    @Override
    public String toString() {
        return "Certification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfCompletion='" + dateOfCompletion + '\'' +
                '}';
    }
}
