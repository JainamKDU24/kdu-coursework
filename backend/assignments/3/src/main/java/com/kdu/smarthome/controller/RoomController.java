package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.reponse.RoomAddedDTO;
import com.kdu.smarthome.dto.request.AddRoomDTO;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {


    private final RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomAddedDTO> addRoomToHouse(@RequestParam String houseId, @RequestBody AddRoomDTO addRoomDTO) {
        RoomAddedDTO apiResponseDTO=roomService.addRoomToHouse(houseId, addRoomDTO);
        return ResponseEntity.ok(apiResponseDTO);
    }
}

