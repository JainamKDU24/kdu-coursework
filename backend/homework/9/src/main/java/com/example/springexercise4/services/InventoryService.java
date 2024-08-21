package com.example.springexercise4.services;
import com.example.springexercise4.dto.request.VehicleRequestDTO;
import com.example.springexercise4.dto.response.VehicleResponseDTO;
import com.example.springexercise4.exceptions.custom.myexception;
import com.example.springexercise4.repository.InventoryStore;
import com.example.springexercise4.entities.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Objects;

@Slf4j
@Service
public class InventoryService {

    @Autowired
    InventoryStore inventoryStore;

    /**
     * @param vehicleRequestDTO
     * Adds vehicle to the InventoruStore
     */
    public void addtoInventory(VehicleRequestDTO vehicleRequestDTO) {
        if (Objects.isNull(getVehicle(vehicleRequestDTO.getId()))) {
            Vehicle vehicle = Vehicle.builder()
                    .id(vehicleRequestDTO.getId())
                    .name(vehicleRequestDTO.getName())
                    .tyre(vehicleRequestDTO.getTyre())
                    .speaker(vehicleRequestDTO.getSpeaker())
                    .price(vehicleRequestDTO.getPrice())
                    .build();
            inventoryStore.getVehicles().add(vehicle);
            log.info("Vehicle added with id:{} ",vehicleRequestDTO.getId());
        }
        else {
            log.error("Vehicle with id :{} ",vehicleRequestDTO.getId()+" already exists");
            throw new myexception("Vehicle with id :"+vehicleRequestDTO.getId()+" already exists");
        }
    }



    /**
     * @param id
     * @return Vehicle from the ImventoryStore
     */
    public VehicleResponseDTO getVehicle(int id) {
        for (Vehicle v : inventoryStore.getVehicles()) {
            if (v.getId()==id) {
                log.info("Vehicle retrieved with id:{} ",id);
                return VehicleResponseDTO.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .tyre(v.getTyre())
                        .speaker(v.getSpeaker())
                        .price(v.getPrice())
                        .build();
            }
        }
        log.error("Vehicle with id :{} ",id+" already exists");
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
                log.info("Vehicle with id :{} ",vehicleRequestDTO.getId()+" updated");
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
        log.error("Vehicle with id :{} ",id+" does not exists");
        throw new myexception("Vehicle with id :"+id+" does not exists");
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
                log.info("Vehicle with id :{} ",id+" deleted");
                iterator.remove();
                return true; // Vehicle found and deleted
            }
        }
        log.error("Vehicle with id :{} ",id+" does not exists");
        throw new myexception("Vehicle with id :"+id+" does not exists");
    }
}

