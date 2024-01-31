package com.example.springjdbc.service;

import com.example.springjdbc.dao.TenantDao;
import com.example.springjdbc.dto.TenantDataDTO;
import com.example.springjdbc.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing tenant-related operations.
 */
@Service
public class TenantService {
    TenantDao tenantDao;

    ShiftService shiftService;

    UserService userService;

    ShiftTypeService shiftTypeService;


    ShiftUserService shiftUserService;


    @Autowired
    public TenantService(TenantDao tenantDao,ShiftService shiftService,UserService userService,ShiftTypeService shiftTypeService,ShiftUserService shiftUserService){
        this.tenantDao = tenantDao;
        this.shiftService=shiftService;
        this.userService=userService;
        this.shiftTypeService=shiftTypeService;
        this.shiftUserService=shiftUserService;
    }

    /**
     * Retrieves all tenants.
     *
     * @return The list of all tenants.
     */
    public List<Tenant> getAllTenants() {
        return tenantDao.getAllTenants();
    }

    /**
     * Saves data for all entities associated with a tenant in one transaction.
     * If any failure occurs during the process, the transaction will be rolled back.
     *
     * @param tenantData The TenantDataDTO containing data for all entities associated with a tenant
     */
    @Transactional
    public void saveTenantData(TenantDataDTO tenantData) {
        tenantDao.saveTenant(tenantData.getTenant());

        shiftService.saveShift(tenantData.getShift());
        userService.saveUser(tenantData.getUser());
        shiftTypeService.saveShiftType(tenantData.getShiftType());
        shiftUserService.saveShiftUser(tenantData.getShiftUser());
    }
}
