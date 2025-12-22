package com.example.demo.controller;

import com.example.demo.entity.Warranty;
import com.example.demo.service.WarrantyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warranties")
public class WarrantyController {

    private final WarrantyService service;

    public WarrantyController(WarrantyService service) {
        this.service = service;
    }

    @PostMapping("/register/{userId}/{productId}")
    public Warranty register(@PathVariable Long userId,
                             @PathVariable Long productId,
                             @RequestBody Warranty w) {
        return service.registerWarranty(userId, productId, w);
    }

    @GetMapping("/{id}")
    public Warranty get(@PathVariable Long id) {
        return service.getWarranty(id);
    }

    @GetMapping("/user/{userId}")
    public List<Warranty> userWarranties(@PathVariable Long userId) {
        return service.getUserWarranties(userId);
    }
}
