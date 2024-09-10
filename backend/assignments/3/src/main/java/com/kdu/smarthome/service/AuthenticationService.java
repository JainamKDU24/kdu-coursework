package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.dto.reponse.RegisterResponseDTO;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.repo.UserRepo;
import com.kdu.smarthome.utils.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user authentication-related operations.
 */
@Service
public class AuthenticationService {

    private final UserRepo repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtSecurityProviderService jwtSecurityProviderService;

    /**
     * Constructs an instance of the AuthenticationService.
     *
     * @param repository      The repository for user-related database operations.
     * @param passwordEncoder The password encoder for encoding user passwords.
     * @param jwtSecurityProviderService      The JWT service for generating authentication tokens.
     */
    public AuthenticationService(UserRepo repository, PasswordEncoder passwordEncoder, JwtSecurityProviderService jwtSecurityProviderService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtSecurityProviderService = jwtSecurityProviderService;
    }

    /**
     * Registers a new user with the provided user data.
     *
     * @param userDTO The user data for registration.
     * @return A RegisterResponseDTO containing the registration status and JWT token.
     */
    public RegisterResponseDTO register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User user = repository.save(UserUtil.dtotoEntity(userDTO));

        String jwtToken = jwtSecurityProviderService.generateToken(user);

        return new RegisterResponseDTO("User registered successfully", jwtToken);
    }
}
