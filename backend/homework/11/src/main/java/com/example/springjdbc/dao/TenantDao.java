package com.example.springjdbc.dao;

import com.example.springjdbc.mapper.TenantMapper;
import com.example.springjdbc.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Data Access Object (DAO) class for managing Tenant entities in the database.
 */
@Component
public class TenantDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public Tenant getTenant(UUID tenantId) {
        String sql = "SELECT * FROM tenants WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{tenantId}, new TenantMapper());
    }

    public void saveTenant(Tenant tenant) {
        String sql = "INSERT INTO tenants (id, name, created_at, created_by, updated_at, updated_by, time_zone) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), tenant.getName(), new Timestamp(System.currentTimeMillis()), tenant.getCreatedBy(),
                tenant.getUpdatedAt(), tenant.getUpdatedBy(), tenant.getTimeZone());
    }

    /**
     * Retrieves all Tenant entities from the database.
     *
     * @return a list of all Tenant entities
     */
    public List<Tenant> getAllTenants() {
        String sql = "SELECT * FROM tenants";
        return jdbcTemplate.query(sql, new TenantMapper());
    }
}
