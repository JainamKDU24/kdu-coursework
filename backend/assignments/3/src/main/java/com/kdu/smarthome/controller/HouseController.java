package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.reponse.HouseDetailsDTO;
import com.kdu.smarthome.dto.reponse.HouseGetResponseDTO;
import com.kdu.smarthome.dto.reponse.HouseResponseDTO;
import com.kdu.smarthome.dto.request.AddUserToHouseDTO;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.request.HouseDTO;
import com.kdu.smarthome.dto.request.UpdateAddressDTO;
import com.kdu.smarthome.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;
    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<HouseResponseDTO> addHouse(@RequestHeader String authorization, @RequestBody HouseDTO houseDTO) {
        return ResponseEntity.ok(houseService.addHouse(houseDTO,authorization));
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<ApiResponseDTO> addUserToHouse(@RequestHeader String authorization,@PathVariable("houseId") String houseId, @RequestBody AddUserToHouseDTO addUserToHouseDTO) throws JsonProcessingException {
        ApiResponseDTO apiResponseDTO=houseService.addUserToHouse(houseId, addUserToHouseDTO,authorization);
        return ResponseEntity.ok(apiResponseDTO);
    }

    @GetMapping
    public ResponseEntity<HouseGetResponseDTO> getAllHouses() {
        HouseGetResponseDTO houses = houseService.getAllHouses();
        return ResponseEntity.ok(houses);
    }

    @PutMapping
    public ResponseEntity<ApiResponseDTO> updateHouseAddress(@RequestHeader String authorization,@RequestParam String houseId, @RequestParam UpdateAddressDTO houseDTO) {
        ApiResponseDTO updatedHouse=houseService.updateHouseAddress(houseId, houseDTO,authorization);
        return ResponseEntity.ok(updatedHouse);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<HouseDetailsDTO> getHouseDetails(@PathVariable("houseId") String houseId) {
        HouseDetailsDTO apiResponseDTO = houseService.getHouseDetails(houseId);
        return ResponseEntity.ok(apiResponseDTO);
    }
}