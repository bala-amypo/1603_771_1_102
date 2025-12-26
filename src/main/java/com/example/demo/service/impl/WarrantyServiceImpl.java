package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.warrantyRepository = warrantyRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Warranty registerWarranty(Long userId, Long productId, Warranty warranty) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (warranty.getExpiryDate() == null ||
            warranty.getPurchaseDate() == null ||
            !warranty.getExpiryDate().isAfter(warranty.getPurchaseDate())) {
            throw new IllegalArgumentException(
                    "Expiry date must be after purchase date");
        }

        if (warrantyRepository.existsBySerialNumber(
                warranty.getSerialNumber())) {
            throw new IllegalArgumentException(
                    "Serial number must be unique");
        }

        warranty.setUser(user);
        warranty.setProduct(product);

        return warrantyRepository.save(warranty);
    }

    @Override
    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepository.findByUserId(userId);
    }

    @Override
    public Warranty getWarranty(Long warrantyId) {
        return warrantyRepository.findById(warrantyId)
                .orElseThrow(() ->
                        new RuntimeException("Warranty not found"));
    }
}
