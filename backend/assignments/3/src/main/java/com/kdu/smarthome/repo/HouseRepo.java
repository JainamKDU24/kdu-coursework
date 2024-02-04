package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HouseRepo extends JpaRepository<House, String> {
}
