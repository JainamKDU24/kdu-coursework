package com.example.springjpa.service;

import com.example.springjpa.entity.ShiftUser;
import com.example.springjpa.exception.custom.MyCustomException;
import com.example.springjpa.exception.custom.ShiftUserDeletionInvalid;
import com.example.springjpa.exception.custom.ShiftUserNotFound;
import com.example.springjpa.repository.ShiftUserRepository;
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

    private final ShiftUserRepository shiftUserRepository;

    @Autowired
    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
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
            shiftUserRepository.save(shiftUser);
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
        return shiftUserRepository.findByTenantId(tenantId);
    }

    @Transactional
    public void deleteShiftUserByShiftEndsAt(UUID userId) throws ShiftUserNotFound {
        ShiftUser shiftUser = shiftUserRepository.findById(userId)
                .orElseThrow(() -> new ShiftUserNotFound("ShiftUser not found with ID: " + userId));

        // Check if the shift end time is at 23:00 UTC
        if (shiftUser.getShift().getTimeEnd().getHours() == 23 && shiftUser.getShift().getTimeEnd().getMinutes() == 0) {
            shiftUserRepository.delete(shiftUser);
        } else {
            throw new ShiftUserDeletionInvalid("ShiftUser cannot be deleted as it does not meet the criteria");
        }
    }
}
