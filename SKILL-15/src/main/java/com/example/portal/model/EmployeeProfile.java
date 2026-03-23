package com.example.portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_profiles")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String department;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserAccount user;

    public EmployeeProfile(String fullName, String department, UserAccount user) {
        this.fullName = fullName;
        this.department = department;
        this.user = user;
    }
}
