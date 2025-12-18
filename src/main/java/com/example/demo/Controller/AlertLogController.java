package com.example.controller;

import com.example.model.AlertLog;
import com.example.service.AlertLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AlertLogController {

    private final AlertLogService logService;

    public AlertLogController(AlertLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/{warrantyId}")
    public AlertLog addLog(@PathVariable Long warrantyId,
                           @RequestBody String message) {
        return logService.addLog(warrantyId, message);
    }

    @GetMapping("/{warrantyId}")
    public List<AlertLog> getLogs(@PathVariable Long warrantyId) {
        return logService.getLogs(warrantyId);
    }
}
