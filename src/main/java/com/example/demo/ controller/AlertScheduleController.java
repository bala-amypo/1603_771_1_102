package com.example.demo.controller;

import com.example.demo.entity.AlertSchedule;
import com.example.demo.service.AlertScheduleService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alert-schedules")
public class AlertScheduleController {

    private final AlertScheduleService alertScheduleService;

    public AlertScheduleController(AlertScheduleService alertScheduleService) {
        this.alertScheduleService = alertScheduleService;
    }

    // POST /alert-schedules/{warrantyId}
    @PostMapping("/{warrantyId}")
    public AlertSchedule createSchedule(
            @PathVariable Long warrantyId,
            @RequestBody AlertSchedule schedule) {

        return alertScheduleService.createSchedule(warrantyId, schedule);
    }

    // GET /alert-schedules/{warrantyId}
    @GetMapping("/{warrantyId}")
    public List<AlertSchedule> getSchedules(
            @PathVariable Long warrantyId) {

        return alertScheduleService.getSchedules(warrantyId);
    }
}
