package com.example.portal.config;

import com.example.portal.model.EmployeeProfile;
import com.example.portal.model.Role;
import com.example.portal.model.UserAccount;
import com.example.portal.repository.EmployeeProfileRepository;
import com.example.portal.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final UserAccountRepository userAccountRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        if (userAccountRepository.count() > 0) {
            return;
        }

        var admin = new UserAccount("admin", passwordEncoder.encode("admin123"), Role.ADMIN);
        userAccountRepository.save(admin);

        var employee = new UserAccount("employee1", passwordEncoder.encode("emp123"), Role.EMPLOYEE);
        userAccountRepository.save(employee);

        employeeProfileRepository.save(new EmployeeProfile("Admin User", "IT", admin));
        employeeProfileRepository.save(new EmployeeProfile("Employee One", "Finance", employee));
    }
}
