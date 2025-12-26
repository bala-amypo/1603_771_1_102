package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.impl.ProductServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setBrand(dto.getBrand());
        p.setModelNumber(dto.getModelNumber());
        p.setCategory(dto.getCategory());

        return productService.addProduct(p);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
