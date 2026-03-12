package com.example.model;

/**
 * Simplified Student class for demonstration
 * In a real Spring application, this would have @Component and @Autowired annotations
 */
public class StudentDemo {
    private int id;
    private String name;
    private String gender;
    private CertificationDemo certification;  // This would be autowired in Spring

    public StudentDemo() {
        this.id = 1001;
        this.name = "Raj Kumar";
        this.gender = "Male";
        // In Spring, this would be automatically injected
        this.certification = new CertificationDemo();
    }

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

    public CertificationDemo getCertification() {
        return certification;
    }

    public void setCertification(CertificationDemo certification) {
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
