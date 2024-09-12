package kdu.assessment.assessment2.service;

import kdu.assessment.assessment2.entity.Cart;
import kdu.assessment.assessment2.exception.custom.CartNotFound;
import kdu.assessment.assessment2.exception.custom.MyCustomException;
import kdu.assessment.assessment2.exception.custom.ProductNotFound;
import kdu.assessment.assessment2.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void saveCart(Cart cart) {
        try {
            cartRepository.save(cart);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save product.");
        }
    }
    @Transactional
    public void updateCart(int cartId, Cart cart) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart existingCart = optionalCart.get();
            existingCart.setProduct(cart.getProduct());
            existingCart.setUser(cart.getUser());
            existingCart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            cartRepository.save(existingCart);
        } else {
            throw new MyCustomException("Cart with ID " + cartId + " does not exist.");
        }
    }
    public List<Cart> findById(Integer productId) {
        return cartRepository.findAllById(productId);
    }
    @Transactional
    public void deleteCart(Integer cartId) throws ProductNotFound {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFound("Cart not found with ID: " + cartId));
        cartRepository.delete(cart);
    }
}
