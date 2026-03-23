package com.example.portal.dto;

public record EmployeeProfileResponse(String username,
                                       String fullName,
                                       String department,
                                       String role) {
}
