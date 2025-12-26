package com.example.demo.controller;

import com.example.demo.dto.AlertLogDTO;
import com.example.demo.entity.AlertLog;
import com.example.demo.service.impl.AlertLogServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts/logs")
public class AlertLogController {

    private final AlertLogServiceImpl logService;

    public AlertLogController(AlertLogServiceImpl logService) {
        this.logService = logService;
    }

    @PostMapping("/warranty/{warrantyId}")
    public AlertLog addLog(@PathVariable Long warrantyId,
                           @Valid @RequestBody AlertLogDTO dto) {
        return logService.addLog(warrantyId, dto.getMessage());
    }

    @GetMapping("/warranty/{warrantyId}")
    public List<AlertLog> getLogs(@PathVariable Long warrantyId) {
        return logService.getLogs(warrantyId);
    }
}
