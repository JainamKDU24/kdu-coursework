package com.example.springjdbc.service;

import com.example.springjdbc.dao.ShiftDao;
import com.example.springjdbc.entity.Shifts;
import com.example.springjdbc.exception.custom.MyCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing shift-related operations.
 */
@Service
public class ShiftService {

    ShiftDao shiftDao;
    @Autowired
    public ShiftService(ShiftDao shiftDao){
        this.shiftDao=shiftDao;
    }

    /**
     * Saves a shift.
     *
     * @param shift The shift to be saved.
     * @throws MyCustomException If an error occurs during the shift saving process.
     */
    @Transactional
    public void saveShift(Shifts shift) {
        try {
            shiftDao.saveShift(shift);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save shift.");
        }
    }

    /**
     * Retrieves all shifts belonging to a specific tenant.
     *
     * @param tenantId The ID of the tenant.
     * @return The list of shifts belonging to the specified tenant.
     */
    public List<Shifts> getShifts(UUID tenantId) {
        return shiftDao.getShifts(tenantId);
    }
}
