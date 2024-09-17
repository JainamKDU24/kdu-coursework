package com.example.springjdbc.controller;

import com.example.springjdbc.entity.*;
import com.example.springjdbc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Endpoint for retrieving Shift entities by tenant ID.
     *
     * @param tenantId the UUID of the tenant
     * @return ResponseEntity containing a list of Shift entities
     */
    @GetMapping("/shifts/{tenantId}")
    public ResponseEntity<List<Shifts>> getShifts(@PathVariable UUID tenantId) {
        List<Shifts> shifts = shiftService.getShifts(tenantId);
        return ResponseEntity.ok(shifts);
    }

    /**
     * Endpoint for saving a User entity.
     *
     * @param user the User entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody Users user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User saved successfully");
    }

    /**
     * Endpoint for retrieving User entities by tenant ID.
     *
     * @param tenantId the UUID of the tenant
     * @return ResponseEntity containing a list of User entities
     */
    @GetMapping("/users/{tenantId}")
    public ResponseEntity<List<Users>> getUsers(@PathVariable UUID tenantId) {
        List<Users> users = userService.getUsers(tenantId);
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint for retrieving a User entity by ID.
     *
     * @param userId the UUID of the user
     * @return ResponseEntity containing the User entity
     */
    @GetMapping("/usersbyId/{userId}")
    public ResponseEntity<Users> getUsersbyId(@PathVariable UUID userId) {
        Users users = userService.getUserById(userId);
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint for updating a User entity.
     *
     * @param userId the UUID of the user to update
     * @param user the updated User entity
     * @return ResponseEntity indicating the success of the operation
     */
    @PutMapping("/updateuser/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody Users user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok("User updated successfully");
    }

    /**
     * Endpoint for saving a ShiftType entity.
     *
     * @param shiftType the ShiftType entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/shift-types")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftTypes shiftType) {
        shiftTypeService.saveShiftType(shiftType);
        return ResponseEntity.ok("ShiftType saved successfully");
    }

    /**
     * Endpoint for retrieving ShiftType entities by tenant ID.
     *
     * @param tenantId the UUID of the tenant
     * @return ResponseEntity containing a list of ShiftType entities
     */
    @GetMapping("/shift-types/{tenantId}")
    public ResponseEntity<List<ShiftTypes>> getShiftTypes(@PathVariable UUID tenantId) {
        List<ShiftTypes> shiftTypes = shiftTypeService.getShiftTypes(tenantId);
        return ResponseEntity.ok(shiftTypes);
    }

    /**
     * Endpoint for saving a ShiftUser entity.
     *
     * @param shiftUser the ShiftUser entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/shift-users")
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.saveShiftUser(shiftUser);
        return ResponseEntity.ok("ShiftUser saved successfully");
    }

    /**
     * Endpoint for retrieving ShiftUser entities by tenant ID.
     *
     * @param tenantId the UUID of the tenant
     * @return ResponseEntity containing a list of ShiftUser entities
     */
    @GetMapping("/shift-users/{tenantId}")
    public ResponseEntity<List<ShiftUser>> getShiftUsers(@PathVariable UUID tenantId) {
        List<ShiftUser> shiftUsers = shiftUserService.getShiftUsers(tenantId);
        return ResponseEntity.ok(shiftUsers);
    }

    /**
     * Endpoint for retrieving all Tenant entities.
     *
     * @return ResponseEntity containing a list of Tenant entities
     */
    @GetMapping("/tenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }
}
