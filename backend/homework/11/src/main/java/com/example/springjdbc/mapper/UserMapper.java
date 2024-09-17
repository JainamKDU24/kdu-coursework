package com.example.springjdbc.mapper;

import com.example.springjdbc.entity.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping database rows to Users entities.
 */
public class UserMapper implements RowMapper<Users> {

    /**
     * Maps a row from the ResultSet to a Users entity.
     *
     * @param rs the ResultSet containing the data to be mapped
     * @param rowNum the number of the current row
     * @return the Users entity mapped from the ResultSet
     * @throws SQLException if an SQL exception occurs while accessing the ResultSet
     */
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users user = new Users();
        user.setId(rs.getObject("id", UUID.class));
        user.setUsername(rs.getString("username"));
        user.setLoggedIn(rs.getShort("loggedin"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setCreatedBy(rs.getString("created_by"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        user.setUpdatedBy(rs.getString("updated_by"));
        user.setTimeZone(rs.getString("time_zone"));
        user.setTenantId(rs.getObject("tenant_id", UUID.class));
        return user;
    }
}
