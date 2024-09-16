package com.example.springjpa.repository;

import com.example.springjpa.entity.Shifts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftRepository extends JpaRepository<Shifts, UUID> {

    List<Shifts> findByTenantId(UUID tenantId);
    @Query("SELECT s FROM Shifts s WHERE s.dateStart = :dateStart AND s.dateEnd = :dateEnd ORDER BY s.name ASC")
    List<Shifts> findTop3Shifts(@Param("dateStart") LocalDate startDate, @Param("dateEnd") LocalDate endDate, Pageable pageable);
}
