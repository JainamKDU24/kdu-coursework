package com.example.springjdbc.service;

import com.example.springjdbc.dao.ShiftTypeDao;
import com.example.springjdbc.entity.ShiftTypes;
import com.example.springjdbc.exception.custom.MyCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing shift type-related operations.
 */
@Service
public class ShiftTypeService {
    ShiftTypeDao shiftTypeDao;

    @Autowired
    public ShiftTypeService(ShiftTypeDao shiftTypeDao){
        this.shiftTypeDao=shiftTypeDao;
    }

    /**
     * Saves a shift type.
     *
     * @param shiftType The shift type to be saved.
     * @throws MyCustomException If an error occurs during the shift type saving process.
     */
    @Transactional
    public void saveShiftType(ShiftTypes shiftType) {
        try {
            shiftTypeDao.saveShiftType(shiftType);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save shift type.");
        }
    }

    /**
     * Retrieves all shift types belonging to a specific tenant.
     *
     * @param tenantId The ID of the tenant.
     * @return The list of shift types belonging to the specified tenant.
     */
    public List<ShiftTypes> getShiftTypes(UUID tenantId) {
        return shiftTypeDao.getShiftTypes(tenantId);
    }
}
