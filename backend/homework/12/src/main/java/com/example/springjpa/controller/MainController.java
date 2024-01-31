package com.example.springjpa.controller;

import com.example.springjpa.entity.*;
import com.example.springjpa.exception.custom.ShiftUserNotFound;
import com.example.springjpa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing endpoints related to Tenants and associated entities.
 */
@RestController
@RequestMapping("/api")
public class MainController {
    TenantService tenantService;

    ShiftService shiftService;

    UserService userService;

    ShiftTypeService shiftTypeService;

    ShiftUserService shiftUserService;


    @Autowired
    public MainController(TenantService tenantService,ShiftService shiftService,UserService userService,ShiftTypeService shiftTypeService,ShiftUserService shiftUserService){
        this.tenantService = tenantService;
        this.shiftService=shiftService;
        this.userService=userService;
        this.shiftTypeService=shiftTypeService;
        this.shiftUserService=shiftUserService;
    }

    @GetMapping("/tenants/get")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }

    /**
     * Endpoint for saving a Shift entity.
     *
     * @param shift the Shift entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/shifts")
    public ResponseEntity<String> saveShift(@RequestBody Shifts shift) {
        shiftService.saveShift(shift);
        return ResponseEntity.ok("Shift saved successfully");
    }

    /**
     * Endpoint for saving a User entity.
     *
     * @param user the User entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User saved successfully");
    }

    /**
     * Endpoint for saving a ShiftType entity.
     *
     * @param shiftType the ShiftType entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/shift-types")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.saveShiftType(shiftType);
        return ResponseEntity.ok("ShiftType saved successfully");
    }

    /**
     * Saves a ShiftUser entity.
     *
     * @param shiftUser The ShiftUser entity to be saved.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PostMapping("/shift-users")
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.saveShiftUser(shiftUser);
        return ResponseEntity.ok("ShiftUser saved successfully");
    }
    @GetMapping("/users/all")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {

        // Validate page and size parameters
        if (page < 0) {
            page = 0;
        }

        if (size < 1 || size > 50) {
            size = 50;
        }

        // Create pageable object for pagination
        Pageable pageable = PageRequest.of(page, size);

        // Retrieve users from UserService with pagination
        Page<User> usersPage = userService.findAllUsers(pageable);

        return ResponseEntity.ok(usersPage);
    }
    @DeleteMapping("/shift-users/{userId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID userId) throws ShiftUserNotFound {
        shiftUserService.deleteShiftUserByShiftEndsAt(userId);
        return ResponseEntity.ok("ShiftUser deleted successfully");
    }
    @GetMapping("/top-shifts")
    public ResponseEntity<List<Shifts>> getTop3Shifts() {
        LocalDate dateStart = LocalDate.of(2023, 1, 1);
        LocalDate dateEnd = LocalDate.of(2023, 1, 25);


        List<Shifts> top3Shifts = shiftService.findTop3ShiftsByDateRange(dateStart, dateEnd);

        return ResponseEntity.ok(top3Shifts);
    }
    @PutMapping("/userupdate/{userId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable UUID userId, @RequestBody User user){
        userService.updateUserDetails(userId, user);
        return ResponseEntity.ok("User details updated successfully");
    }
}
