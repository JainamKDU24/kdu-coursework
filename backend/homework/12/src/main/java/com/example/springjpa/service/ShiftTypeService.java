package com.example.springjpa.service;

import com.example.springjpa.entity.ShiftType;
import com.example.springjpa.exception.custom.MyCustomException;
import com.example.springjpa.repository.ShiftTypeRepository;
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

    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    /**
     * Saves a shift type.
     *
     * @param shiftType The shift type to be saved.
     * @throws MyCustomException If an error occurs during the shift type saving process.
     */
    @Transactional
    public void saveShiftType(ShiftType shiftType) {
        try {
            shiftTypeRepository.save(shiftType);
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
    public List<ShiftType> getShiftTypes(UUID tenantId) {
        return shiftTypeRepository.findByTenantId(tenantId);
    }
}
