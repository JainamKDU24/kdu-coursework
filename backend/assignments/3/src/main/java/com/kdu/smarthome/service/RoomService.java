package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.reponse.RoomAddedDTO;
import com.kdu.smarthome.dto.request.AddRoomDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.custom.InvalidRequest;
import com.kdu.smarthome.exception.custom.NotFound;
import com.kdu.smarthome.repo.HouseRepo;
import com.kdu.smarthome.repo.RoomRepo;
import com.kdu.smarthome.utils.RoomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class RoomService {
    private final HouseRepo houseRepo;
    private final RoomRepo roomRepo;

    @Autowired
    public RoomService(HouseRepo houseRepo,RoomRepo roomRepo) {
        this.roomRepo=roomRepo;
        this.houseRepo=houseRepo;
    }

    /**
     * Adds a room to a given house.
     *
     * @param houseId    The ID of the house to which the room will be added.
     * @param addRoomDTO The DTO containing information about the room to be added.
     * @return A DTO containing details of the added room.
     * @throws NotFound       If the house does not exist.
     * @throws InvalidRequest If an error occurs while adding the room.
     */
    public RoomAddedDTO addRoomToHouse(String houseId, AddRoomDTO addRoomDTO) {
        House house = houseRepo.findById(houseId).orElseThrow(() -> new NotFound("House does not exist"));

        Room room = RoomUtil.dtotoEntity(addRoomDTO, house);

        try {
            return RoomUtil.roomAddRequest(roomRepo.save(room), "Room added to given house", HttpStatus.OK);
        } catch (Exception e) {
            throw new InvalidRequest("Failed to add room");
        }
    }

}
