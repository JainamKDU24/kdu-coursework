package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.reponse.GetInventoryDTO;
import com.kdu.smarthome.dto.request.AddToInventoryDTO;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.exception.custom.CouldNotGet;
import com.kdu.smarthome.exception.custom.InvalidRequest;
import com.kdu.smarthome.repo.InventoryRepo;
import com.kdu.smarthome.utils.InventoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    @Autowired
    public InventoryService(InventoryRepo inventoryRepo) {
        this.inventoryRepo=inventoryRepo;
    }
    /**
     * Retrieves a list of all inventory items.
     *
     * @return A GetInventoryDTO containing the list of inventory items.
     * @throws CouldNotGet if the inventory items list cannot be fetched.
     */
    public GetInventoryDTO getInventory(){
        try{
            List<Inventory> inventories=inventoryRepo.findAll();
            return InventoryUtil.requesttoGetResponse(inventories, HttpStatus.OK);
        }catch (Exception e){
            throw new CouldNotGet("Failed to fetch Inventory Items list");
        }
    }
    /**
     * Adds a new item to the inventory.
     *
     * @param inventory The details of the item to be added.
     * @return An ApiResponseDTO containing the status and details of the added item.
     * @throws InvalidRequest if the item cannot be added to the inventory.
     */
    public ApiResponseDTO addInventory(AddToInventoryDTO inventory){
        try {
            Inventory item=inventoryRepo.save(InventoryUtil.dtotoEntity(inventory));
            return InventoryUtil.requesttoResponse("Item added to inventory successfully",item,HttpStatus.OK);
        }catch (Exception e){
            throw new InvalidRequest("Failed to add item to inventory.");
        }
    }
}
