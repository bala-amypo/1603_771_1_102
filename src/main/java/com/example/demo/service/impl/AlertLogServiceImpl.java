package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AlertLogService;
import java.util.List;

public class AlertLogServiceImpl implements AlertLogService {

    private final AlertLogRepository repo;
    private final WarrantyRepository warrantyRepo;

    public AlertLogServiceImpl(AlertLogRepository r, WarrantyRepository w) {
        this.repo = r;
        this.warrantyRepo = w;
    }

    public AlertLog addLog(Long warrantyId, String message) {
        Warranty w = warrantyRepo.findById(warrantyId).orElseThrow(RuntimeException::new);
        AlertLog log = AlertLog.builder().warranty(w).message(message).build();
        return repo.save(log);
    }

    public List<AlertLog> getLogs(Long warrantyId) {
        warrantyRepo.findById(warrantyId).orElseThrow(RuntimeException::new);
        return repo.findByWarrantyId(warrantyId);
    }
}
