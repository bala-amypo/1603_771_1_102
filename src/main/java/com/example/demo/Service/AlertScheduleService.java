package com.example.service;

import com.example.model.AlertSchedule;
import com.example.model.Warranty;
import com.example.repository.AlertScheduleRepository;
import com.example.repository.WarrantyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduleService {

    private final AlertScheduleRepository scheduleRepository;
    private final WarrantyRepository warrantyRepository;

    public AlertScheduleService(AlertScheduleRepository scheduleRepository,
                                WarrantyRepository warrantyRepository) {
        this.scheduleRepository = scheduleRepository;
        this.warrantyRepository = warrantyRepository;
    }

    public AlertSchedule createSchedule(Long warrantyId, AlertSchedule schedule) {
        Warranty warranty = warrantyRepository.findById(warrantyId)
                .orElseThrow(() -> new RuntimeException("Warranty not found"));

        schedule.setWarranty(warranty);
        return scheduleRepository.save(schedule);
    }

    public List<AlertSchedule> getSchedules(Long warrantyId) {
        return scheduleRepository.findByWarrantyId(warrantyId);
    }
}
