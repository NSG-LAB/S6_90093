package com.example.auth.config;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DemoDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        seedDemoAccount();
    }

    private void seedDemoAccount() {
        String username = "demo";
        if (userRepository.existsByUsername(username)) {
            return;
        }

        User demo = new User();
        demo.setUsername(username);
        demo.setPassword("demo123");
        demo.setFullName("Demo Account");
        demo.setEmail("demo@example.com");
        userRepository.save(demo);
    }
}
