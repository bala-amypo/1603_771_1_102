package com.example.demo.util;

import com.example.demo.exception.BadRequestException;

public class ModelValidator {

    private ModelValidator() {
        // Utility class – prevent object creation
    }

    /* ================= USER VALIDATION ================= */

    public static void validateUser(String name, String email, String password) {

        if (name == null || name.trim().isEmpty()) {
            throw new BadRequestException("User name is required");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new BadRequestException("Email is required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new BadRequestException("Password is required");
        }
    }

    /* ================= PRODUCT VALIDATION ================= */

    public static void validateProduct(String name, Double price) {

        if (name == null || name.trim().isEmpty()) {
            throw new BadRequestException("Product name is required");
        }

        if (price == null || price <= 0) {
            throw new BadRequestException("Product price must be greater than zero");
        }
    }

    /* ================= WARRANTY VALIDATION ================= */

    public static void validateWarranty(Object startDate, Object endDate) {

        if (startDate == null) {
            throw new BadRequestException("Warranty start date is required");
        }
