package com.example.springjdbc.mapper;

import com.example.springjdbc.entity.Tenant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping database rows to Tenant entities.
 */
public class TenantMapper implements RowMapper<Tenant> {

    /**
     * Maps a row from the ResultSet to a Tenant entity.
     *
     * @param rs the ResultSet containing the data to be mapped
     * @param rowNum the number of the current row
     * @return the Tenant entity mapped from the ResultSet
     * @throws SQLException if an SQL exception occurs while accessing the ResultSet
     */
    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.fromString(rs.getString("id")));
        tenant.setName(rs.getString("name"));
        tenant.setCreatedAt(rs.getTimestamp("created_at"));
        tenant.setCreatedBy(rs.getString("created_by"));
        tenant.setUpdatedAt(rs.getTimestamp("updated_at"));
        tenant.setUpdatedBy(rs.getString("updated_by"));
        tenant.setTimeZone(rs.getString("time_zone"));
        return tenant;
    }
}
