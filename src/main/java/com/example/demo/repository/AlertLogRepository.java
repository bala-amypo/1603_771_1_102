package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AlertLog;

public interface AlertLogRepository extends JpaRepository<AlertLog, Long> {

    // Get logs for a warranty
    List<AlertLog> findByWarrantyId(Long warrantyId);
}
