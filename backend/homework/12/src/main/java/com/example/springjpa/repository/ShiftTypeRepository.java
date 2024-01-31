package com.example.springjpa.repository;

import com.example.springjpa.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftTypeRepository extends JpaRepository<ShiftType, UUID> {
    List<ShiftType> findByTenantId(UUID tenantId);
}
