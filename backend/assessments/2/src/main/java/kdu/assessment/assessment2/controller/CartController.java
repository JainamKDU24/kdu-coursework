package kdu.assessment.assessment2.controller;

import kdu.assessment.assessment2.entity.Cart;
import kdu.assessment.assessment2.entity.Product;
import kdu.assessment.assessment2.exception.custom.ProductNotFound;
import kdu.assessment.assessment2.service.CartService;
import kdu.assessment.assessment2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService=cartService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody Cart cart) {
        cartService.saveCart(cart);
        return ResponseEntity.ok("Cart added successfully");
    }
    @GetMapping("/get")
    public ResponseEntity<List<Cart>> getAllUsers(@RequestParam Integer id) {
        List<Cart> carts = cartService.findById(id);
        return ResponseEntity.ok(carts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Integer id, @RequestBody Cart cart){
        cartService.updateCart(id, cart);
        return ResponseEntity.ok("Cart details updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFound {
        cartService.deleteCart(id);
        return ResponseEntity.ok("Cart deleted successfully");
    }
}
