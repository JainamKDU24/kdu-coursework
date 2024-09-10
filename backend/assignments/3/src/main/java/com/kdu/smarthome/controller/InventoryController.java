package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.dto.reponse.GetInventoryDTO;
import com.kdu.smarthome.dto.request.AddToInventoryDTO;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<GetInventoryDTO> getInventory() {
        return ResponseEntity.ok(inventoryService.getInventory());
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> addInventory(@RequestBody AddToInventoryDTO addToInventoryDTO) {
        ApiResponseDTO apiResponseDTO=inventoryService.addInventory(addToInventoryDTO);
        return ResponseEntity.ok(apiResponseDTO);
    }
}
