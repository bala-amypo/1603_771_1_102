package com.example.demo.service.impl;

import com.example.demo.entity.AlertLog;
import com.example.demo.repository.AlertLogRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLogServiceImpl implements AlertLogService {

    private final AlertLogRepository repo;
    private final WarrantyRepository warrantyRepo;

    public AlertLogServiceImpl(AlertLogRepository repo,
                               WarrantyRepository warrantyRepo) {
        this.repo = repo;
        this.warrantyRepo = warrantyRepo;
    }

    public AlertLog addLog(Long warrantyId, String message) {
        AlertLog log = AlertLog.builder()
                .warranty(warrantyRepo.findById(warrantyId).orElseThrow())
                .message(message)
                .build();
        return repo.save(log);
    }

    public List<AlertLog> getLogs(Long warrantyId) {
        return repo.findByWarrantyId(warrantyId);
    }
}
