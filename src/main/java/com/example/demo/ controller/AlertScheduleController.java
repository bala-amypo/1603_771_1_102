package com.example.demo.controller;

import com.example.demo.dto.AlertScheduleDTO;
import com.example.demo.entity.AlertSchedule;
import com.example.demo.service.impl.AlertScheduleServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts/schedules")
public class AlertScheduleController {

    private final AlertScheduleServiceImpl scheduleService;

    public AlertScheduleController(AlertScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/warranty/{warrantyId}")
    public AlertSchedule createSchedule(@PathVariable Long warrantyId,
                                        @Valid @RequestBody AlertScheduleDTO dto) {

        AlertSchedule s = new AlertSchedule();
        s.setDaysBeforeExpiry(dto.getDaysBeforeExpiry());
        s.setEnabled(dto.isEnabled());

        return scheduleService.createSchedule(warrantyId, s);
    }

    @GetMapping("/warranty/{warrantyId}")
    public List<AlertSchedule> getSchedules(@PathVariable Long warrantyId) {
        return scheduleService.getSchedules(warrantyId);
    }
}
