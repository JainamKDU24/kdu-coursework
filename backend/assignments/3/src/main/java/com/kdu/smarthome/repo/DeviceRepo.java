package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, String> {
    List<Device> findByHouse_Id(String houseId);
}
