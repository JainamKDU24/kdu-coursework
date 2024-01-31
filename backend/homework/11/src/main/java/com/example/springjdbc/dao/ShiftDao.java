package com.example.springjdbc.dao;

import com.example.springjdbc.mapper.ShiftMapper;
import com.example.springjdbc.entity.Shifts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) class for managing Shifts entities in the database.
 */
@Component
public class ShiftDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Saves a Shift entity to the database.
     *
     * @param shift the Shift entity to be saved
     */
    public void saveShift(Shifts shift) {
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, time_start, time_end, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shift.getShiftTypeId(), shift.getName(), shift.getDateStart(), shift.getDateEnd(), shift.getTimeStart(), shift.getTimeEnd(), new Timestamp(System.currentTimeMillis()), shift.getCreatedBy(), shift.getUpdatedAt(), shift.getUpdatedBy(), shift.getTimeZone(), shift.getTenantId());
    }

    /**
     * Retrieves all Shift entities associated with a given tenant from the database.
     *
     * @param tenantId the UUID of the tenant
     * @return a list of Shift entities associated with the given tenant
     */
    public List<Shifts> getShifts(UUID tenantId) {
        String sql = "SELECT * FROM shifts WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, new ShiftMapper());
    }
}
