package com.kdu.smarthome.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.reponse.HouseDetailsDTO;
import com.kdu.smarthome.dto.reponse.HouseGetResponseDTO;
import com.kdu.smarthome.dto.reponse.HouseResponseDTO;
import com.kdu.smarthome.dto.request.HouseDTO;
import com.kdu.smarthome.dto.request.UpdateAddressDTO;
import com.kdu.smarthome.entity.House;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Utility class for House-related operations.
 */
public class HouseUtil {
    private HouseUtil() {}
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts HouseDTO to a House entity.
     *
     * @param houseDTO The HouseDTO object to convert.
     * @return The House entity created from the HouseDTO.
     */
    public static House dtotoEntity(HouseDTO houseDTO) {
        return House.builder()
                .house_name(houseDTO.getHouse_name())
                .address(houseDTO.getAddress())
                .build();
    }

    public static House dtotoHouse(UpdateAddressDTO houseDTO) {
        return House.builder()
                .address(houseDTO.getAddress())
                .build();
    }

    /**
     * Creates an ApiResponseDTO for general House-related responses.
     *
     * @param message The message to include in the response.
     * @param object  The Object associated with the response.
     * @param status  The HTTP status of the response.
     * @return An ApiResponseDTO representing the House-related response.
     */
    public static ApiResponseDTO requesttoResponse(String message, Object object, HttpStatus status) throws JsonProcessingException {
        return ApiResponseDTO.builder()
                .message(message)
                .object(mapper.writeValueAsString(object))
                .httpStatus(status)
                .build();
    }

    /**
     * Creates a HouseResponseDTO for House-specific responses.
     *
     * @param message The message to include in the response.
     * @param house   The House object associated with the response.
     * @param status  The HTTP status of the response.
     * @return A HouseResponseDTO representing the House-specific response.
     */
    public static HouseResponseDTO requesttoHouseResponse(String message, House house, HttpStatus status) {
        return HouseResponseDTO.builder()
                .message(message)
                .house(house)
                .httpStatus(status)
                .build();
    }

    /**
     * Creates a HouseGetResponseDTO for responses containing a list of houses.
     *
     * @param houseList The list of House objects to include in the response.
     * @param message   The message to include in the response.
     * @param status    The HTTP status of the response.
     * @return A HouseGetResponseDTO representing the response containing a list of houses.
     * @throws JsonProcessingException if there is an error in JSON processing.
     */
    public static HouseGetResponseDTO requesttoHouseGetResponse(List<House> houseList, String message, HttpStatus status) throws JsonProcessingException {
        return HouseGetResponseDTO.builder()
                .houses(mapper.writeValueAsString(houseList))
                .message(message)
                .httpStatus(status)
                .build();
    }

    /**
     * Creates a HouseDetailsDTO for responses containing details of a house.
     *
     * @param houseList The list of objects (rooms and devices) associated with the house.
     * @param message   The message to include in the response.
     * @param httpStatus    The HTTP status of the response.
     * @return A HouseDetailsDTO representing the response containing details of a house.
     * @throws JsonProcessingException if there is an error in JSON processing.
     */
    public static HouseDetailsDTO reponseForHouseDetails(List<Object> houseList, String message, HttpStatus httpStatus) throws JsonProcessingException {
        return HouseDetailsDTO.builder()
                .message(message)
                .roomsAndDevices(mapper.writeValueAsString(houseList))
                .httpStatus(httpStatus)
                .build();
    }
}
