package com.example.springjpa.repository;

import com.example.springjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByTenantId(UUID tenantId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET username = :username, time_zone = :time_zone WHERE id = :id", nativeQuery = true)
    int updateUserDetails(@Param("username") String username, @Param("time_zone") String timeZone,@Param("id") UUID userId);
}

