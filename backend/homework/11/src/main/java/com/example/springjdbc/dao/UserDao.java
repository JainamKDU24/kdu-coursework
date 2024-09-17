package com.example.springjdbc.dao;

import com.example.springjdbc.mapper.UserMapper;
import com.example.springjdbc.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) class for managing User entities in the database.
 */
@Component
public class UserDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves a User entity in the database.
     *
     * @param user the User entity to save
     */
    @Transactional
    public void saveUser(Users user) {
        String sql = "INSERT INTO users (id, username, loggedin, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), user.getUsername(), user.getLoggedIn(), new Timestamp(System.currentTimeMillis()), user.getCreatedBy(), user.getUpdatedAt(), user.getUpdatedBy(), user.getTimeZone(), user.getTenantId());
    }

    /**
     * Retrieves all User entities belonging to a specific tenant from the database.
     *
     * @param tenantId the UUID of the tenant
     * @return a list of User entities belonging to the specified tenant
     */
    public List<Users> getUsers(UUID tenantId) {
        String sql = "SELECT * FROM users WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, new UserMapper());
    }

    /**
     * Retrieves a specific User entity by its ID from the database.
     *
     * @param userId the UUID of the user
     * @return the User entity with the specified ID
     */
    public Users getUserbyId(UUID userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserMapper());
    }
}
