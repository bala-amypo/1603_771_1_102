package com.example.demo.service.impl;

import com.example.demo.entity.AlertLog;
import com.example.demo.repository.AlertLogRepository;
import com.example.demo.service.AlertLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertLogServiceImpl implements AlertLogService {

    private final AlertLogRepository repository;

    public AlertLogServiceImpl(AlertLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public AlertLog save(AlertLog alertLog) {
        AlertLog log = new AlertLog();
        log.setMessage(alertLog.getMessage());
        log.setStatus(alertLog.getStatus());
        return repository.save(log);
    }

    @Override
    public List<AlertLog> getAll() {
        return repository.findAll();
    }
}
