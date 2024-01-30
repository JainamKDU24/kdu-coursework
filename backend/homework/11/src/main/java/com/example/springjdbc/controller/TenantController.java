package com.example.springjdbc.controller;

import com.example.springjdbc.dto.TenantDataDTO;
import com.example.springjdbc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/tenant-data")
    public ResponseEntity<String> saveTenantData(@RequestBody TenantDataDTO tenantData) {
        try {
            // Call the service method to save all tenant data
            tenantService.saveTenantData(tenantData);
            return ResponseEntity.ok("Tenant data saved successfully");
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save tenant data");
        }
    }
}