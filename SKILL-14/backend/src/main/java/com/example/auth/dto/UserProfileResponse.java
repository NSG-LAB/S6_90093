package com.example.auth.dto;

import java.time.Instant;

public class UserProfileResponse {

    private Long id;
    private String username;
    private String fullName;
    private String email;
    private Instant createdAt;

    public UserProfileResponse(Long id, String username, String fullName, String email, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
