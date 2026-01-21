package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Java-based configuration class for Spring application.
 * This replaces XML configuration with annotated Java code.
 * 
 * @Configuration - Indicates this is a configuration class containing bean definitions.
 * @ComponentScan - Enables automatic detection of beans marked with @Component, @Service, etc.
 *                  The basePackage specifies where to scan for annotated components.
 */
@Configuration
@ComponentScan(basePackages = "com.example.model")
public class AppConfig {
    // This class acts as a configuration source for Spring.
    // Spring will automatically create beans for all @Component annotated classes
    // in the specified package (com.example.model).
}
