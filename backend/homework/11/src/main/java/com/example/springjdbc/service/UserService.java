package com.example.springjdbc.service;

import com.example.springjdbc.dao.UserDao;
import com.example.springjdbc.entity.Users;
import com.example.springjdbc.exception.custom.MyCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Saves a user.
     *
     * @param user The user to be saved.
     * @throws MyCustomException If an error occurs during the user saving process.
     */
    @Transactional
    public void saveUser(Users user) {
        try {
            userDao.saveUser(user);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save user.");
        }
    }

    /**
     * Retrieves all users belonging to a specific tenant.
     *
     * @param tenantId The ID of the tenant.
     * @return The list of users belonging to the specified tenant.
     */
    public List<Users> getUsers(UUID tenantId) {
        return userDao.getUsers(tenantId);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user with the specified ID, if found; otherwise, null.
     */
    public Users getUserById(UUID userId){
        return userDao.getUserbyId(userId);
    }

    /**
     * Updates a user.
     *
     * @param userId The ID of the user to update.
     * @param user The updated user object.
     * @throws MyCustomException If an error occurs during the user update process.
     */
    @Transactional
    public void updateUser(UUID userId, Users user) {
        try {
            String sql = "UPDATE users SET username = ?, tenant_id = ?, updated_by = ?, updated_at = ? WHERE id = ?";
            jdbcTemplate.update(sql, user.getUsername(), user.getTenantId(), user.getUpdatedBy(), new Timestamp(System.currentTimeMillis()), userId);
        } catch (Exception e) {
            throw new MyCustomException("Failed to update user.");
        }
    }
}
