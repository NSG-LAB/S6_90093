package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Student POJO class for Spring Dependency Injection demonstration
 * Supports both Constructor Injection and Setter Injection
 */
@Entity
public class Student {

    @Id
    private int studentId;
    private String name;
    private String course;
    private int year;

    // Default Constructor
    public Student() {
    }

    // Constructor for Constructor Injection (all fields)
    public Student(int studentId, String name, String course, int year) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    // Getter and Setter methods
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", year=" + year +
                '}';
    }
}
