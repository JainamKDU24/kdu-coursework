package kdu.assessment.assessment2.service;

import kdu.assessment.assessment2.entity.Orders;
import kdu.assessment.assessment2.exception.custom.MyCustomException;
import kdu.assessment.assessment2.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void saveCart(Orders order) {
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save order.");
        }
    }
}
