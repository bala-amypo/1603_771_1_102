package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AlertScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduleServiceImpl implements AlertScheduleService {

    private final AlertScheduleRepository scheduleRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertScheduleServiceImpl(
            AlertScheduleRepository scheduleRepository,
            WarrantyRepository warrantyRepository) {
        this.scheduleRepository = scheduleRepository;
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public AlertSchedule createSchedule(
            Long warrantyId,
            AlertSchedule schedule) {

        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Warranty not found"));

        schedule.setWarranty(warranty);
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<AlertSchedule> getSchedules(Long warrantyId) {
        return scheduleRepository.findByWarrantyId(warrantyId);
    }
}
