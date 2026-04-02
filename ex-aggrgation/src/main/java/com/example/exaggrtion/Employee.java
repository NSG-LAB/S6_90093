package com.example.exaggrtion;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private int id;
    private String name;
    private double salary;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
