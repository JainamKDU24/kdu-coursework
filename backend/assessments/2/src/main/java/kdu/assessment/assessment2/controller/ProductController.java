package kdu.assessment.assessment2.controller;

import kdu.assessment.assessment2.entity.Product;
import kdu.assessment.assessment2.exception.custom.ProductNotFound;
import kdu.assessment.assessment2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> saveUser(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }
    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllUsers(@RequestParam Integer id) {
        List<Product> products = productService.findById(id);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserDetails(@PathVariable Integer id, @RequestBody Product product){
        productService.updateProduct(id, product);
        return ResponseEntity.ok("Product details updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) throws ProductNotFound {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
