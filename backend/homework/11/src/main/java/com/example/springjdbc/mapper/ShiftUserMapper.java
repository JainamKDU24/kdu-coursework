package com.example.springjdbc.mapper;

import com.example.springjdbc.entity.ShiftUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping database rows to ShiftUser entities.
 */
public class ShiftUserMapper implements RowMapper<ShiftUser> {

    /**
     * Maps a row from the ResultSet to a ShiftUser entity.
     *
     * @param rs the ResultSet containing the data to be mapped
     * @param rowNum the number of the current row
     * @return the ShiftUser entity mapped from the ResultSet
     * @throws SQLException if an SQL exception occurs while accessing the ResultSet
     */
    @Override
    public ShiftUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(rs.getObject("id", UUID.class));
        shiftUser.setShiftId(rs.getObject("shift_id", UUID.class));
        shiftUser.setUserId(rs.getObject("user_id", UUID.class));
        shiftUser.setCreatedAt(rs.getTimestamp("created_at"));
        shiftUser.setCreatedBy(rs.getString("created_by"));
        shiftUser.setUpdatedAt(rs.getTimestamp("updated_at"));
        shiftUser.setUpdatedBy(rs.getString("updated_by"));
        shiftUser.setTenantId(rs.getObject("tenant_id", UUID.class));
        return shiftUser;
    }
}
