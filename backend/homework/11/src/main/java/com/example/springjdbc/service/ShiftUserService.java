package com.example.springjdbc.service;

import com.example.springjdbc.dao.ShiftUserDao;
import com.example.springjdbc.entity.ShiftUser;
import com.example.springjdbc.exception.custom.MyCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing shift user-related operations.
 */
@Service
public class ShiftUserService {

    ShiftUserDao shiftUserDao;
    @Autowired
    public ShiftUserService(ShiftUserDao shiftUserDao){
        this.shiftUserDao=shiftUserDao;
    }

    /**
     * Saves a shift user.
     *
     * @param shiftUser The shift user to be saved.
     * @throws MyCustomException If an error occurs during the shift user saving process.
     */
    @Transactional
    public void saveShiftUser(ShiftUser shiftUser) {
        try {
            shiftUserDao.saveShiftUser(shiftUser);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save shift user.");
        }
    }

    /**
     * Retrieves all shift users belonging to a specific tenant.
     *
     * @param tenantId The ID of the tenant.
     * @return The list of shift users belonging to the specified tenant.
     */
    public List<ShiftUser> getShiftUsers(UUID tenantId) {
        return shiftUserDao.getShiftUsers(tenantId);
    }
}
