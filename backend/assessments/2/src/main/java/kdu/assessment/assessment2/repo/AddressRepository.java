package kdu.assessment.assessment2.repo;

import kdu.assessment.assessment2.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findAllById(Integer userId);
}
