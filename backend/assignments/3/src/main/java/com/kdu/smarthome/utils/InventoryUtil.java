package com.kdu.smarthome.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.reponse.GetInventoryDTO;
import com.kdu.smarthome.dto.request.AddToInventoryDTO;
import com.kdu.smarthome.entity.Inventory;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Utility class for Inventory-related operations.
 */
public class InventoryUtil {

    private InventoryUtil() {}
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Converts AddToInventoryDTO to an Inventory entity.
     *
     * @param addToInventoryDTO The AddToInventoryDTO object to convert.
     * @return The Inventory entity created from the AddToInventoryDTO.
     */
    public static Inventory dtotoEntity(AddToInventoryDTO addToInventoryDTO) {
        return Inventory.builder()
                .kickstonid(addToInventoryDTO.getKickston_id())
                .deviceusername(addToInventoryDTO.getDevice_username())
                .devicepassword(addToInventoryDTO.getDevice_password())
                .manufacturedatetime(addToInventoryDTO.getManufacture_date_time())
                .manufacturefactoryplace(addToInventoryDTO.getManufacture_factory_place())
                .build();
    }

    /**
     * Creates an ApiResponseDTO for general Inventory-related responses.
     *
     * @param message The message to include in the response.
     * @param object  The Object associated with the response.
     * @param status  The HTTP status of the response.
     * @return An ApiResponseDTO representing the Inventory-related response.
     */
    public static ApiResponseDTO requesttoResponse(String message, Object object, HttpStatus status) throws JsonProcessingException {
        return ApiResponseDTO.builder()
                .message(message)
                .object(mapper.writeValueAsString(object))
                .httpStatus(status)
                .build();
    }

    /**
     * Creates a GetInventoryDTO for responses containing a list of inventory items.
     *
     * @param inventoryList The list of Inventory objects to include in the response.
     * @param status    The HTTP status of the response.
     * @return A GetInventoryDTO representing the response containing a list of inventory items.
     * @throws JsonProcessingException if there is an error in JSON processing.
     */
    public static GetInventoryDTO requesttoGetResponse(List<Inventory> inventoryList, HttpStatus status) throws JsonProcessingException {
        return GetInventoryDTO.builder()
                .inventory(mapper.writeValueAsString(inventoryList))
                .httpStatus(status)
                .build();
    }
}
