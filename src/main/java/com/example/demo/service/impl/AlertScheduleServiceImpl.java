package com.example.demo.service.impl;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.repository.AlertScheduleRepository;
import com.example.demo.repository.WarrantyRepository;
import com.example.demo.service.AlertScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduleServiceImpl implements AlertScheduleService {

    private final AlertScheduleRepository repo;
    private final WarrantyRepository warrantyRepo;

    public AlertScheduleServiceImpl(AlertScheduleRepository repo,
                                    WarrantyRepository warrantyRepo) {
        this.repo = repo;
        this.warrantyRepo = warrantyRepo;
    }

    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule s) {
        s.setWarranty(warrantyRepo.findById(warrantyId).orElseThrow());
        return repo.save(s);
    }

    public List<AlertSchedule> getSchedules(Long warrantyId) {
        return repo.findByWarrantyId(warrantyId);
    }
}
