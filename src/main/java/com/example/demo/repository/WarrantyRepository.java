package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Warranty;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {

    // Check unique serial number
    boolean existsBySerialNumber(String serialNumber);

    // Get warranties for a user
    List<Warranty> findByUserId(Long userId);

    // HQL-style derived query
    List<Warranty> findWarrantiesExpiringBetween(LocalDate from, LocalDate to);
}
