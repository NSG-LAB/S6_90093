package com.example.portal.controller;

import com.example.portal.dto.EmployeeProfileResponse;
import com.example.portal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/profile")
    public ResponseEntity<EmployeeProfileResponse> profile(Authentication authentication) {
        return ResponseEntity.ok(employeeService.getProfile(authentication.getName()));
    }
}
