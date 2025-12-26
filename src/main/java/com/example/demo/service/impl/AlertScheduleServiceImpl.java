package com.example.demo.service.impl;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.entity.Warranty;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AlertScheduleRepository;
import com.example.demo.repository.WarrantyRepository;

import java.util.List;

public class AlertScheduleServiceImpl {

    private final AlertScheduleRepository scheduleRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertScheduleServiceImpl(AlertScheduleRepository scheduleRepository,
                                    WarrantyRepository warrantyRepository) {
        this.scheduleRepository = scheduleRepository;
        this.warrantyRepository = warrantyRepository;
    }

    public AlertSchedule createSchedule(Long warrantyId,
                                        AlertSchedule schedule) {

        if (schedule.getDaysBeforeExpiry() < 0) {
            throw new ValidationException(
                    "daysBeforeExpiry cannot be negative");
        }

        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Warranty not found"));

        schedule.setWarranty(warranty);
        return scheduleRepository.save(schedule);
    }

    public List<AlertSchedule> getSchedules(Long warrantyId) {

        warrantyRepository.findById(warrantyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Warranty not found"));

        return scheduleRepository.findByWarrantyId(warrantyId);
    }
}
