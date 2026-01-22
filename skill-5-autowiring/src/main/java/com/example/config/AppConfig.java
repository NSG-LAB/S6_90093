package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig - Java-based Spring Configuration.
 * Uses @Configuration to mark this as a configuration class.
 * Uses @ComponentScan to automatically detect and register beans marked with @Component.
 */
@Configuration
@ComponentScan(basePackages = "com.example.model")
public class AppConfig {
    // Configuration is complete with annotations above.
    // No additional bean definitions needed for this basic example.
}
