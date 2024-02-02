package kdu.assessment.assessment2.controller;

import kdu.assessment.assessment2.entity.Orders;
import kdu.assessment.assessment2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody Orders order) {
        orderService.saveCart(order);
        return ResponseEntity.ok("Order placed successfully");
    }
}

