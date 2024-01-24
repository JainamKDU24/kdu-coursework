package com.example.Springexercise3.services;
import com.example.Springexercise3.DTO.Request.VehicleRequestDTO;
import com.example.Springexercise3.DTO.Response.VehicleResponseDTO;
import com.example.Springexercise3.Logging;
import com.example.Springexercise3.repository.InventoryStore;
import com.example.Springexercise3.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Locale;


@Service
public class InventoryService {
    Logging logger=new Logging();

    @Autowired
    InventoryStore inventoryStore;

    /**
     * @param vehicleRequestDTO
     * Adds vehicle to the InventoruStore
     */
    public void addtoInventory(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = Vehicle.builder()
                .id(vehicleRequestDTO.getId())
                .name(vehicleRequestDTO.getName())
                .tyre(vehicleRequestDTO.getTyre())
                .speaker(vehicleRequestDTO.getSpeaker())
                .price(vehicleRequestDTO.getPrice())
                .build();

        inventoryStore.getVehicles().add(vehicle);
        logger.logInfo("Vehicle Added with id: "+ vehicle.getId() +" Name: "+vehicle.getName());
    }


    /**
     * @param name
     * @return Vehicle from the ImventoryStore
     */
    public VehicleResponseDTO getVehicle(String name) {
        for (Vehicle v : inventoryStore.getVehicles()) {
            if (v.getName().equals(name)) {
                return VehicleResponseDTO.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .tyre(v.getTyre())
                        .speaker(v.getSpeaker())
                        .price(v.getPrice())
                        .build();
            }
        }
        return null;
    }

    /**
     * @param id
     * @param vehicleRequestDTO
     * @return the Updated Vehicle Details
     */
    public VehicleResponseDTO updateVehicle(int id, VehicleRequestDTO vehicleRequestDTO) {
        for (Vehicle v : inventoryStore.getVehicles()) {
            if (v.getId() == id) {
                v.setName(vehicleRequestDTO.getName());
                v.setTyre(vehicleRequestDTO.getTyre());
                v.setSpeaker(vehicleRequestDTO.getSpeaker());
                v.setPrice(vehicleRequestDTO.getPrice());

                // Return the updated vehicle details
                return VehicleResponseDTO.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .tyre(v.getTyre())
                        .speaker(v.getSpeaker())
                        .price(v.getPrice())
                        .build();
            }
        }
        return null;
    }

    /**
     * @param id
     * @return boolean
     */
    public boolean deleteVehicle(int id) {
        Iterator<Vehicle> iterator = inventoryStore.getVehicles().iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getId() == id) {
                iterator.remove();
                return true; // Vehicle found and deleted
            }
        }
        return false; // Vehicle not found
    }

}

