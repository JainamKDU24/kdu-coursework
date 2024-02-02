package kdu.assessment.assessment2.config;

import kdu.assessment.assessment2.filter.TokenGeneratorFilter;
import kdu.assessment.assessment2.filter.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration class for custom security configuration in the Spring application.
 */
@Configuration
public class CustomSecurityConfig {

    /**
     * Defines a custom security filter chain with configured filters and authorization rules.
     *
     * @param http The HttpSecurity object to configure security rules.
     * @return A SecurityFilterChain object representing the custom security filter chain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception {
        http.
                addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/products/add","/users/add","/products/delete","/product/update").hasRole("ADMIN")
                        .requestMatchers("/users/get","/users/update","/products/get","/cart/**","/orders/**").hasRole("USER")
                        .anyRequest().authenticated()).csrf().disable();
        http.httpBasic(withDefaults());
        return http.build();
    }

    /**
     * Creates a BCryptPasswordEncoder bean for password encoding.
     *
     * @return A PasswordEncoder object using BCrypt algorithm.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
