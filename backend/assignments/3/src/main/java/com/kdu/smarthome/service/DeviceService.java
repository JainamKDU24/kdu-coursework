package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.request.AddDeviceDTO;
import com.kdu.smarthome.dto.request.RegisterDeviceDTO;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.custom.InvalidRequest;
import com.kdu.smarthome.exception.custom.NotFound;
import com.kdu.smarthome.exception.custom.NotPermitted;
import com.kdu.smarthome.repo.DeviceRepo;
import com.kdu.smarthome.repo.HouseRepo;
import com.kdu.smarthome.repo.InventoryRepo;
import com.kdu.smarthome.repo.RoomRepo;
import com.kdu.smarthome.utils.DeviceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepo deviceRepo;
    private final HouseRepo houseRepo;
    private final RoomRepo roomRepo;

    private final InventoryRepo inventoryRepo;

    @Autowired
    public DeviceService(DeviceRepo deviceRepo,HouseRepo houseRepo,RoomRepo roomRepo,InventoryRepo inventoryRepo) {
        this.roomRepo=roomRepo;
        this.inventoryRepo=inventoryRepo;
        this.houseRepo=houseRepo;
        this.deviceRepo = deviceRepo;
    }
    /**
     * Saves a new device registration based on the provided device information.
     *
     * @param registerDeviceDTO The DTO containing information about the device to be registered.
     * @return An ApiResponseDTO indicating the result of the operation.
     * @throws NotFound       If the device is not found in the inventory.
     * @throws NotPermitted   If the provided credentials are invalid.
     */
    public ApiResponseDTO saveDevice(RegisterDeviceDTO registerDeviceDTO) {
        try {
            Inventory inventory = inventoryRepo.findById(registerDeviceDTO.getKickston_id()).orElse(null);
            if (Objects.isNull(inventory)) {
                throw new NotFound("Device is not present");
            }
            if (isValidCredentials(inventory, registerDeviceDTO)) {
                Device savedDevice = deviceRepo.save(DeviceUtil.dtotoEntity(registerDeviceDTO));
                return DeviceUtil.requesttoResponse("Device registered successfully", savedDevice, HttpStatus.OK);
            } else {
                throw new InvalidRequest("Invalid credentials. Please check again");
            }
        } catch (NotFound e) {
            throw new NotFound("Device registration not possible. " + e.getMessage());
        } catch (InvalidRequest e) {
            throw new InvalidRequest("Device cannot be registered. " + e.getMessage());
        } catch (Exception e) {
            throw new NotPermitted("Device registration not permitted");
        }
    }

    /**
     * Checks if the provided credentials match the inventory device.
     *
     * @param inventory         The inventory device.
     * @param registerDeviceDTO The DTO containing the device registration information.
     * @return True if the credentials are valid, false otherwise.
     */
    private boolean isValidCredentials(Inventory inventory, RegisterDeviceDTO registerDeviceDTO) {
        return inventory.getDevicepassword().equals(registerDeviceDTO.getDevice_password()) &&
                inventory.getDeviceusername().equals(registerDeviceDTO.getDevice_username());
    }


    /**
     * Adds a device to a specific house and room.
     *
     * @param addDeviceDTO The DTO containing information about the device and its location.
     * @return An ApiResponseDTO indicating the result of the operation.
     * @throws NotFound      If the device, house, or room is not found.
     * @throws InvalidRequest If an error occurs while registering the device.
     */
    public ApiResponseDTO addDeviceToHouse(AddDeviceDTO addDeviceDTO) {
        try {
            Device device = deviceRepo.findById(addDeviceDTO.getKickstonId()).orElseThrow(() ->
                    new NotFound("Device with ID " + addDeviceDTO.getKickstonId() + " does not exist."));

            // Ensure house and room exist before assigning
            House house = houseRepo.findById(addDeviceDTO.getHouseId()).orElseThrow(() ->
                    new NotFound("House with ID " + addDeviceDTO.getHouseId() + " does not exist."));
            Room room = roomRepo.findById(addDeviceDTO.getRoomId()).orElseThrow(() ->
                    new NotFound("Room with ID " + addDeviceDTO.getRoomId() + " does not exist."));

            device.setHouse(house);
            device.setRoom(room);
            device.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            Device savedDevice = deviceRepo.save(device);
            return DeviceUtil.requesttoResponse("Device registered successfully.", savedDevice, HttpStatus.OK);
        } catch (Exception e) {
            throw new InvalidRequest("Failed to register device.");
        }
    }

}
