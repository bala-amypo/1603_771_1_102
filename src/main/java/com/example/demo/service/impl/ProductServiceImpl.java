package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    public Product addProduct(Product p) {
        if (p.getModelNumber() == null)
            throw new IllegalArgumentException("Model number required");
        if (p.getCategory() == null)
            throw new IllegalArgumentException("Category required");
        return repo.save(p);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }
}
