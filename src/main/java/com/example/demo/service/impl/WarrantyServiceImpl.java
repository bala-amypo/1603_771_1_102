package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.Warranty;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WarrantyRepository;

import java.util.List;

public class WarrantyServiceImpl {

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

    public Warranty registerWarranty(Long userId,
                                     Long productId,
                                     Warranty warranty) {

        if (warranty.getExpiryDate()
                .isBefore(warranty.getPurchaseDate())) {
            throw new ValidationException(
                    "Expiry date must be after purchase date");
        }

        if (warrantyRepository
                .existsBySerialNumber(warranty.getSerialNumber())) {
            throw new ValidationException(
                    "Serial number must be unique");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        warranty.setUser(user);
        warranty.setProduct(product);

        return warrantyRepository.save(warranty);
    }

    public Warranty getWarranty(Long id) {
        return warrantyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Warranty not found"));
    }

    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepository.findByUserId(userId);
    }
}
