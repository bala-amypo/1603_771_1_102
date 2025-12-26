package com.example.demo.controller;

import com.example.demo.dto.WarrantyDTO;
import com.example.demo.entity.Warranty;
import com.example.demo.service.impl.WarrantyServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warranties")
public class WarrantyController {

    private final WarrantyServiceImpl warrantyService;

    public WarrantyController(WarrantyServiceImpl warrantyService) {
        this.warrantyService = warrantyService;
    }

    @PostMapping("/user/{userId}/product/{productId}")
    public Warranty registerWarranty(@PathVariable Long userId,
                                     @PathVariable Long productId,
                                     @Valid @RequestBody WarrantyDTO dto) {

        Warranty w = new Warranty();
        w.setPurchaseDate(dto.getPurchaseDate());
        w.setExpiryDate(dto.getExpiryDate());
        w.setSerialNumber(dto.getSerialNumber());

        return warrantyService.registerWarranty(userId, productId, w);
    }

    @GetMapping("/{id}")
    public Warranty getWarranty(@PathVariable Long id) {
        return warrantyService.getWarranty(id);
    }

    @GetMapping("/user/{userId}")
    public List<Warranty> getUserWarranties(@PathVariable Long userId) {
        return warrantyService.getUserWarranties(userId);
    }
}
