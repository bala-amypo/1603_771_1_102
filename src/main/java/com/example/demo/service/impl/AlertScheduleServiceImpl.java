package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.AlertScheduleService;
import java.util.List;

public class AlertScheduleServiceImpl implements AlertScheduleService {

    private final AlertScheduleRepository repo;
    private final WarrantyRepository warrantyRepo;

    public AlertScheduleServiceImpl(AlertScheduleRepository r, WarrantyRepository w) {
        this.repo = r;
        this.warrantyRepo = w;
    }

    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule s) {
        if (s.getDaysBeforeExpiry() < 0)
            throw new IllegalArgumentException("daysBeforeExpiry");

        Warranty w = warrantyRepo.findById(warrantyId).orElseThrow(RuntimeException::new);
        s.setWarranty(w);
        return repo.save(s);
    }

    public List<AlertSchedule> getSchedules(Long warrantyId) {
        warrantyRepo.findById(warrantyId).orElseThrow(RuntimeException::new);
        return repo.findByWarrantyId(warrantyId);
    }
}
