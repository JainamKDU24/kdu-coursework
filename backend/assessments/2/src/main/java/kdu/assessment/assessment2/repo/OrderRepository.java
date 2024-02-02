package kdu.assessment.assessment2.repo;

import kdu.assessment.assessment2.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findAllById(Integer userId);
}
