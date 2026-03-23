package com.example.portal.controller;

import com.example.portal.dto.EmployeeProfileResponse;
import com.example.portal.dto.EmployeeRecordRequest;
import com.example.portal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeProfileResponse> addEmployee(@Valid @RequestBody EmployeeRecordRequest request) {
        return ResponseEntity.ok(employeeService.addEmployee(request));
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String username) {
        employeeService.deleteEmployee(username);
        return ResponseEntity.noContent().build();
    }
}
