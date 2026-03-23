package com.example.portal.repository;

import com.example.portal.model.UserAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}
