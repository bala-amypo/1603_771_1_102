package com.example.service;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WarrantyService(WarrantyRepository warrantyRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.warrantyRepository = warrantyRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Warranty registerWarranty(Long userId, Long productId, Warranty warranty) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        warranty.setUser(user);
        warranty.setProduct(product);

        return warrantyRepository.save(warranty);
    }

    public Warranty getWarranty(Long warrantyId) {
        return warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));
    }

    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepository.findByUserId(userId);
    }
}
