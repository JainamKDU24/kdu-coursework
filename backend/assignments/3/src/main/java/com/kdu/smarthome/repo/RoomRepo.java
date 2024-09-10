package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepo extends JpaRepository<Room, String> {
    List<Room> findByHouse_id(String houseId);
}
