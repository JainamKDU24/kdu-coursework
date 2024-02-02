package kdu.assessment.assessment2.repo;

import kdu.assessment.assessment2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllById(Integer inventoryId);
}