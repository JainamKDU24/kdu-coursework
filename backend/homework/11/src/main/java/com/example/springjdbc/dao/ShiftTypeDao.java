package com.example.springjdbc.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.example.springjdbc.entity.ShiftTypes;
import com.example.springjdbc.mapper.ShiftTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Data Access Object (DAO) class for managing ShiftTypes entities in the database.
 */
@Component
public class ShiftTypeDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftTypeDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves a Shift_Type entity to the database.
     *
     * @param shiftType the Shift_Type entity to be saved
     */
    public void saveShiftType(ShiftTypes shiftType) {
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shiftType.getUqName(), shiftType.getDescription(), shiftType.isActive(), new Timestamp(System.currentTimeMillis()), shiftType.getCreatedBy(), shiftType.getUpdatedAt(), shiftType.getUpdatedBy(), shiftType.getTimeZone(), shiftType.getTenantId());
    }

    /**
     * Retrieves all Shift_Type entities associated with a given tenant from the database.
     *
     * @param tenantId the UUID of the tenant
     * @return a list of Shift_Type entities associated with the given tenant
     */
    public List<ShiftTypes> getShiftTypes(UUID tenantId) {
        String sql = "SELECT * FROM shift_types WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, new ShiftTypeMapper());
    }
}
