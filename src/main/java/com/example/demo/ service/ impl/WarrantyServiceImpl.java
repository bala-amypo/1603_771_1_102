package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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

    public Warranty registerWarranty(Long userId, Long productId, Warranty w) {

        if (warrantyRepo.existsBySerialNumber(w.getSerialNumber()))
            throw new IllegalArgumentException("Serial number must be unique");

        if (!w.getExpiryDate().isAfter(w.getPurchaseDate()))
            throw new IllegalArgumentException("Expiry date must be after purchase date");

        w.setUser(userRepo.findById(userId).orElseThrow());
        w.setProduct(productRepo.findById(productId).orElseThrow());

        return warrantyRepo.save(w);
    }

    public Warranty getWarranty(Long id) {
        return warrantyRepo.findById(id).orElse(null);
    }

    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepo.findByUserId(userId);
    }
}
