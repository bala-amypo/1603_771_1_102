package com.example.controller;

import com.example.model.AlertSchedule;
import com.example.service.AlertScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class AlertScheduleController {

    private final AlertScheduleService scheduleService;

    public AlertScheduleController(AlertScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/{warrantyId}")
    public AlertSchedule createSchedule(@PathVariable Long warrantyId,
                                        @RequestBody AlertSchedule schedule) {
        return scheduleService.createSchedule(warrantyId, schedule);
    }

    @GetMapping("/{warrantyId}")
    public List<AlertSchedule> getSchedules(@PathVariable Long warrantyId) {
        return scheduleService.getSchedules(warrantyId);
    }
}
