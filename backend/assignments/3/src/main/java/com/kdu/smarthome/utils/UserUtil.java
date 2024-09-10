package com.kdu.smarthome.utils;

import com.kdu.smarthome.dto.reponse.RegisterResponseDTO;
import com.kdu.smarthome.dto.request.UserDTO;
import com.kdu.smarthome.entity.User;

/**
 * Utility class for User-related operations.
 */
public class UserUtil {
    private UserUtil() {}

    /**
     * Converts UserDTO to a User entity.
     *
     * @param userDTO The UserDTO object containing user details.
     * @return The User entity created from the UserDTO.
     */
    public static User dtotoEntity(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .emailId(userDTO.getEmailId())
                .build();
    }

}
