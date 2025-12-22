package com.example.demo.repository;

import com.example.demo.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {

    List<Warranty> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);

}
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Warranty;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {

    boolean existsBySerialNumber(String serialNumber);

    List<Warranty> findByUserId(Long userId);
}
