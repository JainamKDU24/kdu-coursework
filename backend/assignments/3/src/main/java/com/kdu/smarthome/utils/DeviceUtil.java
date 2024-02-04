package com.kdu.smarthome.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.request.RegisterDeviceDTO;
import com.kdu.smarthome.entity.Device;
import org.springframework.http.HttpStatus;

/**
 * Utility class for Device-related operations.
 */
public class DeviceUtil {
    private DeviceUtil() {
    }
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts a RegisterDeviceDTO object to a Device entity.
     *
     * @param registerDeviceDTO The RegisterDeviceDTO object to convert.
     * @return The Device entity converted from the RegisterDeviceDTO.
     */
    public static Device dtotoEntity(RegisterDeviceDTO registerDeviceDTO){
        return Device.builder()
                .kickstonid(registerDeviceDTO.getKickston_id())
                .deviceusername(registerDeviceDTO.getDevice_username())
                .devicepassword(registerDeviceDTO.getDevice_password())
                .build();
    }

    /**
     * Creates an ApiResponseDTO for device-related responses.
     *
     * @param message The message to include in the response.
     * @param object The Device object associated with the response.
     * @param status The HTTP status of the response.
     * @return An ApiResponseDTO representing the device-related response.
     */
    public static ApiResponseDTO requesttoResponse(String message, Device object, HttpStatus status) throws JsonProcessingException {
        return ApiResponseDTO.builder()
                .message(message)
                .object(mapper.writeValueAsString(object))
                .httpStatus(status)
                .build();
    }
}
