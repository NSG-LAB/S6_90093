package com.example.portal.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeRecordRequest(@NotBlank(message = "Username is required") String username,
                                    @NotBlank(message = "Password is required") String password,
                                    @NotBlank(message = "Full name is required") String fullName,
                                    @NotBlank(message = "Department is required") String department) {
}
