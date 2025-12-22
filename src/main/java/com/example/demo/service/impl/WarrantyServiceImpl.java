package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.Warranty;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.WarrantyService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepo,
                               UserRepository userRepo,
                               ProductRepository productRepo) {
        this.warrantyRepo = warrantyRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Warranty registerWarranty(Long userId, Long productId, Warranty w) {

        // ✅ Correct: serialNumber is a direct field
        if (warrantyRepo.existsBySerialNumber(w.getSerialNumber())) {
            throw new IllegalArgumentException("Serial number must be unique");
        }

        if (!w.getExpiryDate().isAfter(w.getPurchaseDate())) {
            throw new IllegalArgumentException("Expiry date must be after purchase date");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        w.setUser(user);
        w.setProduct(product);

        return warrantyRepo.save(w);
    }

    @Override
    public Warranty getWarranty(Long id) {
        return warrantyRepo.findById(id).orElse(null);
    }

    @Override
    public List<Warranty> getUserWarranties(Long userId) {
        // ✅ CORRECT for @ManyToOne User
        return warrantyRepo.findByUser_Id(userId);
    }
}
