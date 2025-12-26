package com.example.demo.controller;

import com.example.demo.entity.AlertLog;
import com.example.demo.service.AlertLogService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alert-logs")
public class AlertLogController {

    private final AlertLogService alertLogService;

    public AlertLogController(AlertLogService alertLogService) {
        this.alertLogService = alertLogService;
    }

    // POST /alert-logs/{warrantyId}
    @PostMapping("/{warrantyId}")
    public AlertLog addLog(
            @PathVariable Long warrantyId,
            @RequestBody String message) {

        return alertLogService.addLog(warrantyId, message);
    }

    // GET /alert-logs/{warrantyId}
    @GetMapping("/{warrantyId}")
    public List<AlertLog> getLogs(@PathVariable Long warrantyId) {
        return alertLogService.getLogs(warrantyId);
    }
}
