package com.example.Springexercise3.controller;


import com.example.Springexercise3.DTO.Request.VehicleRequestDTO;
import com.example.Springexercise3.DTO.Response.VehicleResponseDTO;
import com.example.Springexercise3.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    /**
     * @param vehicleRequestDTO
     * @return Message for Successful Operation
     */
    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO){
        inventoryService.addtoInventory(vehicleRequestDTO);
        return ResponseEntity.ok("Vehicle added");
    }

    /**
     * @param name
     * @return Vehicle
     */
    @GetMapping("search/vehicle")
    public VehicleResponseDTO searchVehicle(@RequestParam String name){
        return inventoryService.getVehicle(name);
    }


    /**
     * @param id
     * @param vehicleRequestDTO
     * @return Vehicle with updated details
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable int id, @RequestBody VehicleRequestDTO vehicleRequestDTO) {
        // Call the service method to update the vehicle
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
