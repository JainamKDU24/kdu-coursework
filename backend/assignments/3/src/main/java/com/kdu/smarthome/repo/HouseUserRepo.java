package com.kdu.smarthome.repo;

import com.kdu.smarthome.entity.HouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HouseUserRepo extends JpaRepository<HouseUser, String> {

    @Query(value = "SELECT hu FROM HouseUser hu WHERE hu.house.id = :house_id AND hu.user.username = :username")
    HouseUser findByHouseIdAndUsername(@Param("house_id") String houseId, @Param("username") String username);

    HouseUser findByHouse_IdAndUser_Username(String houseId, String username);
}

