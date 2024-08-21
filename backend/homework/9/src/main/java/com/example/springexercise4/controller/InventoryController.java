package com.example.springexercise4.controller;


import ch.qos.logback.classic.Logger;
import com.example.springexercise4.dto.request.VehicleRequestDTO;
import com.example.springexercise4.dto.response.VehicleResponseDTO;
import com.example.springexercise4.services.InventoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/vehicle")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    /**
     * @param vehicleRequestDTO
     * @return Message for Successful Operation
     */
    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO){
        inventoryService.addtoInventory(vehicleRequestDTO);
        return ResponseEntity.ok("Vehicle added");
    }

    /**
     * @param id
     * @return Vehicle
     */
    @GetMapping("/search")
    public VehicleResponseDTO searchVehicle(@RequestParam int id){
        return inventoryService.getVehicle(id);
    }


    /**
     * @param id
     * @param vehicleRequestDTO
     * @return Vehicle with updated details
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable int id, @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        VehicleResponseDTO updatedVehicle = inventoryService.updateVehicle(id, vehicleRequestDTO);

        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param id
     * @return Message for Successful Operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        boolean isDeleted = inventoryService.deleteVehicle(id);

        if (isDeleted) {
            return ResponseEntity.ok("Vehicle deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
