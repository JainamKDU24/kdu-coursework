package kdu.assessment.assessment2.service;

import kdu.assessment.assessment2.entity.Product;
import kdu.assessment.assessment2.exception.custom.MyCustomException;
import kdu.assessment.assessment2.exception.custom.ProductNotFound;
import kdu.assessment.assessment2.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save product.");
        }
    }
    @Transactional
    public void updateProduct(int productId, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            productRepository.save(existingProduct);
        } else {
            throw new MyCustomException("Product with ID " + productId + " does not exist.");
        }
    }
    public List<Product> findById(Integer productId) {
        return productRepository.findAllById(productId);
    }
    @Transactional
    public void deleteProduct(Integer productId) throws ProductNotFound {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found with ID: " + productId));
        productRepository.delete(product);
    }
}
