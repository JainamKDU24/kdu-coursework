package com.example.springjdbc.dao;

import com.example.springjdbc.entity.ShiftUser;
import com.example.springjdbc.mapper.ShiftUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) class for managing ShiftUser entities in the database.
 */
@Component
public class ShiftUserDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftUserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves a ShiftUser entity to the database.
     *
     * @param shiftUser the ShiftUser entity to be saved
     */
    public void saveShiftUser(ShiftUser shiftUser) {
        String sql = "INSERT INTO shift_user (id, shift_id, user_id, created_at, created_by, updated_at, updated_by, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shiftUser.getShiftId(), shiftUser.getUserId(), new Timestamp(System.currentTimeMillis()), shiftUser.getCreatedBy(), shiftUser.getUpdatedAt(), shiftUser.getUpdatedBy(), shiftUser.getTenantId());
    }

    /**
     * Retrieves all ShiftUser entities associated with a given tenant from the database.
     *
     * @param tenantId the UUID of the tenant
     * @return a list of ShiftUser entities associated with the given tenant
     */
    public List<ShiftUser> getShiftUsers(UUID tenantId) {
        String sql = "SELECT * FROM shift_user WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, new ShiftUserMapper());
    }
}
