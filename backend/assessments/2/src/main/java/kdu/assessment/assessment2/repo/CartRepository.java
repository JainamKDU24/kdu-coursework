package kdu.assessment.assessment2.repo;

import kdu.assessment.assessment2.entity.Cart;
import kdu.assessment.assessment2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllById(Integer cartId);
}
