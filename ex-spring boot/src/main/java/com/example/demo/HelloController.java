package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow React
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }
}
