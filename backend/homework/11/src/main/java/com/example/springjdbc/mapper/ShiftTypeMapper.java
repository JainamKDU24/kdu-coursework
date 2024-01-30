package com.example.springjdbc.mapper;

import com.example.springjdbc.entity.ShiftTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping database rows to ShiftTypes entities.
 */
public class ShiftTypeMapper implements RowMapper<ShiftTypes> {

    /**
     * Maps a row from the ResultSet to a ShiftTypes entity.
     *
     * @param rs the ResultSet containing the data to be mapped
     * @param rowNum the number of the current row
     * @return the ShiftTypes entity mapped from the ResultSet
     * @throws SQLException if an SQL exception occurs while accessing the ResultSet
     */
    @Override
    public ShiftTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftTypes shiftType = new ShiftTypes();
        shiftType.setId(rs.getObject("id", UUID.class));
        shiftType.setUqName(rs.getString("uq_name"));
        shiftType.setDescription(rs.getString("description"));
        shiftType.setActive(rs.getBoolean("active"));
        shiftType.setCreatedAt(rs.getTimestamp("created_at"));
        shiftType.setCreatedBy(rs.getString("created_by"));
        shiftType.setUpdatedAt(rs.getTimestamp("updated_at"));
        shiftType.setUpdatedBy(rs.getString("updated_by"));
        shiftType.setTimeZone(rs.getString("time_zone"));
        shiftType.setTenantId(rs.getObject("tenant_id", UUID.class));
        return shiftType;
    }
}
