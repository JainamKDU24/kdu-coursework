package com.example.springjdbc.mapper;

import com.example.springjdbc.entity.Shifts;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping database rows to Shifts entities.
 */
public class ShiftMapper implements RowMapper<Shifts> {

    /**
     * Maps a row from the ResultSet to a Shifts entity.
     *
     * @param rs the ResultSet containing the data to be mapped
     * @param rowNum the number of the current row
     * @return the Shifts entity mapped from the ResultSet
     * @throws SQLException if an SQL exception occurs while accessing the ResultSet
     */
    @Override
    public Shifts mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shifts shift = new Shifts();
        shift.setId(rs.getObject("id", UUID.class));
        shift.setShiftTypeId(rs.getObject("shift_type_id", UUID.class));
        shift.setName(rs.getString("name"));
        shift.setDateStart(rs.getDate("date_start"));
        shift.setDateEnd(rs.getDate("date_end"));
        shift.setTimeStart(rs.getTime("time_start"));
        shift.setTimeEnd(rs.getTime("time_end"));
        shift.setCreatedAt(rs.getTimestamp("created_at"));
        shift.setCreatedBy(rs.getString("created_by"));
        shift.setUpdatedAt(rs.getTimestamp("updated_at"));
        shift.setUpdatedBy(rs.getString("updated_by"));
        shift.setTimeZone(rs.getString("time_zone"));
        shift.setTenantId(rs.getObject("tenant_id", UUID.class));
        return shift;
    }
}
