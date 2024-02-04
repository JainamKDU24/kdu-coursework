package com.kdu.smarthome.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.reponse.RoomAddedDTO;
import com.kdu.smarthome.dto.request.AddRoomDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import org.springframework.http.HttpStatus;

/**
 * Utility class for Room-related operations.
 */
public class RoomUtil {
    private RoomUtil() {}
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * Converts AddRoomDTO to a Room entity associated with a House.
     *
     * @param addRoomDTO The AddRoomDTO object containing room details.
     * @param house      The House entity to associate the room with.
     * @return The Room entity created from the AddRoomDTO and associated with the House.
     */
    public static Room dtotoEntity(AddRoomDTO addRoomDTO, House house) {
        return Room.builder()
                .room_name(addRoomDTO.getRoom_name())
                .house(house)
                .build();
    }

    /**
     * Creates an ApiResponseDTO for general Room-related responses.
     *
     * @param message The message to include in the response.
     * @param object  The Object associated with the response.
     * @param status  The HTTP status of the response.
     * @return An ApiResponseDTO representing the Room-related response.
     */
    public static ApiResponseDTO requesttoResponse(String message, Object object, HttpStatus status) throws JsonProcessingException {
        return ApiResponseDTO.builder()
                .message(message)
                .object(mapper.writeValueAsString(object))
                .httpStatus(status)
                .build();
    }

    /**
     * Creates a RoomAddedDTO for responses indicating the addition of a room.
     *
     * @param room      The Room entity that was added.
     * @param message   The message to include in the response.
     * @param httpStatus The HTTP status of the response.
     * @return A RoomAddedDTO representing the response for adding a room.
     */
    public static RoomAddedDTO roomAddRequest(Room room, String message, HttpStatus httpStatus) {
        return RoomAddedDTO.builder()
                .message(message)
                .room(room)
                .httpStatus(httpStatus)
                .build();
    }
}
