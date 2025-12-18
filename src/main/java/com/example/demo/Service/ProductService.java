package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        if (product.getModelNumber() == null) {
            throw new RuntimeException("Model number required");
        }
        if (product.getCategory() == null) {
            throw new RuntimeException("Category required");
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
