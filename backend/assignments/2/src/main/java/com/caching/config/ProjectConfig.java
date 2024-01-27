package com.caching.config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for the caching project.
 * This class is responsible for configuring component scanning and enabling caching.
 */
@Configuration
@ComponentScan(basePackages = "com.caching")
@EnableCaching
public class ProjectConfig {
}
