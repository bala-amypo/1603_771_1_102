package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.WarrantyService;
import java.util.List;

public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public WarrantyServiceImpl(WarrantyRepository w, UserRepository u, ProductRepository p) {
        this.warrantyRepo = w;
        this.userRepo = u;
        this.productRepo = p;
    }

    public Warranty registerWarranty(Long userId, Long productId, Warranty w) {

        if (warrantyRepo.existsBySerialNumber(w.getSerialNumber()))
            throw new IllegalArgumentException("Serial number must be unique");

        if (!w.getExpiryDate().isAfter(w.getPurchaseDate()))
            throw new IllegalArgumentException("Expiry date must be after purchase date");

        User u = userRepo.findById(userId).orElseThrow(RuntimeException::new);
        Product p = productRepo.findById(productId).orElseThrow(RuntimeException::new);

        w.setUser(u);
        w.setProduct(p);
        return warrantyRepo.save(w);
    }

    public Warranty getWarranty(Long id) {
        return warrantyRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Warranty> getUserWarranties(Long userId) {
        return warrantyRepo.findByUserId(userId);
    }
}
