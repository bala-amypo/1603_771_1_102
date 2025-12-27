package com.example.demo.util;

public class ModelValidator {

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
