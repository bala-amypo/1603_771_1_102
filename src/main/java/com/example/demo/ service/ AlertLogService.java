package com.example.demo.service;

import com.example.demo.entity.AlertLog;
import java.util.List;

public interface AlertLogService {

    AlertLog save(AlertLog alertLog);

    List<AlertLog> getAll();
}
