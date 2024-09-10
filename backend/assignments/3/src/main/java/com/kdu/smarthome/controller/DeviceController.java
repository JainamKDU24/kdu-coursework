package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceDTO;
import com.kdu.smarthome.dto.request.RegisterDeviceDTO;
import com.kdu.smarthome.dto.reponse.ApiResponseDTO;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO> registerDevice(@RequestBody RegisterDeviceDTO registerDeviceDTO) {
        ApiResponseDTO apiResponseDTO=deviceService.saveDevice(registerDeviceDTO);
        return ResponseEntity.ok(apiResponseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponseDTO> addDeviceToHouse(@RequestBody AddDeviceDTO addDeviceDTO) {
        ApiResponseDTO apiResponseDTO=deviceService.addDeviceToHouse(addDeviceDTO);
        return ResponseEntity.ok(apiResponseDTO);
    }
}