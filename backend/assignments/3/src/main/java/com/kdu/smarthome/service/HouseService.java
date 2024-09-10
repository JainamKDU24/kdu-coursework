package com.kdu.smarthome.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.reponse.HouseDetailsDTO;
import com.kdu.smarthome.dto.reponse.HouseGetResponseDTO;
import com.kdu.smarthome.dto.reponse.HouseResponseDTO;
import com.kdu.smarthome.dto.request.AddUserToHouseDTO;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.request.HouseDTO;
import com.kdu.smarthome.dto.request.UpdateAddressDTO;
import com.kdu.smarthome.entity.*;
import com.kdu.smarthome.enums.Role;
import com.kdu.smarthome.exception.custom.CouldNotGet;
import com.kdu.smarthome.exception.custom.InvalidRequest;
import com.kdu.smarthome.exception.custom.NotFound;
import com.kdu.smarthome.exception.custom.NotPermitted;
import com.kdu.smarthome.repo.*;
import com.kdu.smarthome.utils.HouseUserUtil;
import com.kdu.smarthome.utils.HouseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseService {

    private final HouseUserRepo houseUserRepo;
    private final HouseRepo houseRepo;
    private final UserRepo userRepo;
    private final DeviceRepo deviceRepo;
    private final RoomRepo roomRepo;
    private final JwtSecurityProviderService jwtSecurityProviderService;

    @Autowired
    public HouseService(HouseUserRepo houseUserRepo, HouseRepo houseRepo, UserRepo userRepo, RoomRepo roomRepo, DeviceRepo deviceRepo, JwtSecurityProviderService jwtSecurityProviderService) {
        this.userRepo=userRepo;
        this.houseRepo=houseRepo;
        this.houseUserRepo = houseUserRepo;
        this.roomRepo=roomRepo;
        this.deviceRepo=deviceRepo;
        this.jwtSecurityProviderService = jwtSecurityProviderService;
    }
    /**
     * Adds a new house to the system.
     *
     * @param houseDTO The DTO containing information about the house to be added.
     * @param token    The token for authentication.
     * @return A HouseResponseDTO indicating the result of the operation.
     * @throws NotFound       If the user associated with the token is not found.
     * @throws InvalidRequest If the house cannot be added.
     */
    public HouseResponseDTO addHouse(HouseDTO houseDTO, String token) {
        try {
            User user = extractUserFromToken(token);
            if (!Objects.isNull(user)) {
                House house = houseRepo.save(HouseUtil.dtotoEntity(houseDTO));
                houseUserRepo.save(HouseUserUtil.dtotoEntity(house, user, Role.ADMIN));
                return HouseUtil.requesttoHouseResponse("House added successfully", house, HttpStatus.OK);
            }
            else{
                throw new NotFound("User does not exist.");
            }
        } catch (NotFound e) {
            throw new NotFound("House is not found. " + e.getMessage());
        } catch (Exception e) {
            throw new InvalidRequest("House cannot be added. Please check again.");
        }
    }

    /**
     * Extracts the user associated with the provided token.
     *
     * @param token The token for authentication.
     * @return The user associated with the token, or null if not found.
     */
    private User extractUserFromToken(String token) {
        return userRepo.findById(jwtSecurityProviderService.extractUsername(token.substring(7))).orElse(null);
    }


    /**
     * Adds a user to a house.
     *
     * @param houseId              The ID of the house to add the user to.
     * @param addUserToHouseDTO    The DTO containing the username of the user to be added.
     * @param token                The authentication token.
     * @return A DTO containing details of the added user to the house.
     * @throws NotFound        If the admin user, user, or house is not found.
     * @throws NotPermitted    If the user is not the admin of the house.
     */
    public ApiResponseDTO addUserToHouse(String houseId, AddUserToHouseDTO addUserToHouseDTO, String token) throws JsonProcessingException {
        User admin = userRepo.findById(jwtSecurityProviderService.extractUsername(token.substring(7)))
                .orElseThrow(() -> new NotFound("Admin user does not exist."));

        User user = userRepo.findById(addUserToHouseDTO.getUsername())
                .orElseThrow(() -> new NotFound("User does not exist."));

        House house = houseRepo.findById(houseId)
                .orElseThrow(() -> new NotFound("House with ID " + houseId + " does not exist."));

        HouseUser houseUser = houseUserRepo.findByHouse_IdAndUser_Username(houseId, admin.getUsername());
        if (houseUser.getRole() != Role.ADMIN) {
            throw new NotPermitted("Only admin can add users to the house.", HttpStatus.UNAUTHORIZED);
        }

        HouseUser newUser = HouseUserUtil.dtotoEntity(house, user, Role.USER);
        houseUserRepo.save(newUser);

        return HouseUserUtil.requesttoResponse("User added to House successfully.", newUser, HttpStatus.OK);
    }


    /**
     * Retrieves all houses.
     *
     * @return A DTO containing details of all retrieved houses.
     * @throws CouldNotGet If an error occurs while fetching the house list.
     */
    public HouseGetResponseDTO getAllHouses() {
        try {
            List<House> houses = houseRepo.findAll();
            return HouseUtil.requesttoHouseGetResponse(houses, "Houses that are retrieved.", HttpStatus.OK);
        } catch (Exception e) {
            throw new CouldNotGet("Failed to fetch house list");
        }
    }

    /**
     * Updates the address of a house.
     *
     * @param houseId    The ID of the house to update.
     * @param updateAddressDTO  The DTO containing the updated address information.
     * @param token      The authentication token.
     * @return A DTO containing details of the updated house address.
     * @throws NotFound        If the user, house, or house user is not found.
     * @throws NotPermitted    If the user is not the admin of the house.
     * @throws InvalidRequest  If an error occurs during the house update process.
     */
    public ApiResponseDTO updateHouseAddress(String houseId, UpdateAddressDTO updateAddressDTO, String token) {
        User user = userRepo.findById(jwtSecurityProviderService.extractUsername(token.substring(7)))
                .orElseThrow(() -> new NotFound("User with username does not exist."));

        House house = houseRepo.findById(houseId).orElseThrow(() ->
                new NotFound("House with ID " + houseId + " does not exist."));
        System.out.println("done");

        HouseUser houseUser = houseUserRepo.findByHouseIdAndUsername(houseId, user.getUsername());
        if (Objects.isNull(houseUser)) {
            throw new NotFound("The user " + user.getUsername() + " is not the admin of the house.");
        }

        try {
            house.setAddress(updateAddressDTO.getAddress());
            House updatedHouse = houseRepo.save(house);
            return HouseUtil.requesttoResponse("House address updated successfully.", updatedHouse, HttpStatus.OK);
        } catch (Exception e) {
            throw new InvalidRequest("House cannot be updated, please check again.");
        }
    }

    /**
     * Retrieves details of a house including its rooms and devices.
     *
     * @param houseId The ID of the house.
     * @return A HouseDetailsDTO containing the details of the house, rooms, and devices.
     * @throws NotFound         if the house with the given ID does not exist.
     * @throws InvalidRequest   if the house details cannot be fetched.
     */
    public HouseDetailsDTO getHouseDetails(String houseId){
        try {
            House house = houseRepo.findById(houseId).orElse(null);
            if(Objects.isNull(house)){
                throw new NotFound("House with given id does not exist");
            }
            List<Room> roomList = roomRepo.findByHouse_id(houseId);
            List<Device> deviceList = deviceRepo.findByHouse_Id(houseId);
            List<Object> houseList = new ArrayList<>();
            houseList.addAll(roomList);
            System.out.println(deviceList);
            houseList.addAll(deviceList);
            System.out.println(houseList);
            return HouseUtil.reponseForHouseDetails(houseList,"House details fetched successfully",HttpStatus.OK);
        }
        catch (NotFound e) {
            throw new NotFound("House details cannot be fetched. " + e.getMessage());
        } catch (InvalidRequest | JsonProcessingException e){
            throw new InvalidRequest("House details cannot be fetched please check again");
        }
    }

}
