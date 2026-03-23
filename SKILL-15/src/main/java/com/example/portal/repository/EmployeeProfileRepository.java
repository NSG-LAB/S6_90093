package com.example.portal.repository;

import com.example.portal.model.EmployeeProfile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findByUserUsername(String username);
    void deleteByUserUsername(String username);
}
