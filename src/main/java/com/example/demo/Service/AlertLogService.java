package com.example.service;

import com.example.model.AlertLog;
import com.example.model.Warranty;
import com.example.repository.AlertLogRepository;
import com.example.repository.WarrantyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLogService {

    private final AlertLogRepository logRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertLogService(AlertLogRepository logRepository,
                           WarrantyRepository warrantyRepository) {
        this.logRepository = logRepository;
        this.warrantyRepository = warrantyRepository;
    }

    public AlertLog addLog(Long warrantyId, String message) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));

        AlertLog log = new AlertLog();
        log.setWarranty(warranty);
        log.setMessage(message);

        return logRepository.save(log);
    }

    public List<AlertLog> getLogs(Long warrantyId) {
        return logRepository.findByWarrantyId(warrantyId);
    }
}
