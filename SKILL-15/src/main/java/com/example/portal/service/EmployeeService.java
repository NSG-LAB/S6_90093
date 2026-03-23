package com.example.portal.service;

import com.example.portal.dto.EmployeeProfileResponse;
import com.example.portal.dto.EmployeeRecordRequest;
import com.example.portal.model.EmployeeProfile;
import com.example.portal.model.Role;
import com.example.portal.model.UserAccount;
import com.example.portal.repository.EmployeeProfileRepository;
import com.example.portal.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final UserAccountRepository userAccountRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public EmployeeProfileResponse addEmployee(EmployeeRecordRequest request) {
        if (userAccountRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        UserAccount user = new UserAccount(request.username(), passwordEncoder.encode(request.password()), Role.EMPLOYEE);
        userAccountRepository.save(user);
        EmployeeProfile profile = new EmployeeProfile(request.fullName(), request.department(), user);
        employeeProfileRepository.save(profile);
        return new EmployeeProfileResponse(user.getUsername(), profile.getFullName(), profile.getDepartment(), user.getRole().name());
    }

    @Transactional
    public void deleteEmployee(String username) {
        if (!userAccountRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("User not found");
        }
        employeeProfileRepository.deleteByUserUsername(username);
        userAccountRepository.deleteByUsername(username);
    }

    public EmployeeProfileResponse getProfile(String username) {
        var profile = employeeProfileRepository.findByUserUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        return new EmployeeProfileResponse(
                profile.getUser().getUsername(),
                profile.getFullName(),
                profile.getDepartment(),
                profile.getUser().getRole().name());
    }
}
