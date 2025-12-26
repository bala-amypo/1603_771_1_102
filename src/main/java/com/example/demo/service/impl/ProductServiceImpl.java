package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {

        if (product.getModelNumber() == null ||
                product.getModelNumber().trim().isEmpty()) {
            throw new ValidationException("Model number required");
        }

        if (product.getCategory() == null ||
                product.getCategory().trim().isEmpty()) {
            throw new ValidationException("Category required");
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
