package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AlertSchedule;

public interface AlertScheduleRepository extends JpaRepository<AlertSchedule, Long> {

    // Get schedules for a warranty
    List<AlertSchedule> findByWarrantyId(Long warrantyId);
}
