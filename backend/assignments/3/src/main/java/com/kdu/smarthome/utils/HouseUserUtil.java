package com.kdu.smarthome.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.HouseUser;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.enums.Role;
import org.springframework.http.HttpStatus;

/**
 * Utility class for HouseUser-related operations.
 */
public class HouseUserUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    private HouseUserUtil() {
    }
    /**
     * Converts House, User, and Role parameters to a HouseUser entity.
     *
     * @param house The House object to associate with the HouseUser.
     * @param user  The User object to associate with the HouseUser.
     * @param role  The Role enum representing the role of the user in the house.
     * @return The HouseUser entity created from the parameters.
     */
    public static HouseUser dtotoEntity(House house, User user, Role role) {
        return HouseUser.builder()
                .house(house)
                .user(user)
                .role(role)
                .build();
    }

    /**
     * Creates an ApiResponseDTO for HouseUser-related responses.
     *
     * @param message The message to include in the response.
     * @param object  The Object associated with the response (e.g., HouseUser).
     * @param status  The HTTP status of the response.
     * @return An ApiResponseDTO representing the HouseUser-related response.
     */
    public static ApiResponseDTO requesttoResponse(String message, Object object, HttpStatus status) throws JsonProcessingException {
        return ApiResponseDTO.builder()
                .message(message)
                .object(mapper.writeValueAsString(object))
                .httpStatus(status)
                .build();
    }
}
