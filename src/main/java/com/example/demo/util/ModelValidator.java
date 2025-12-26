package com.example.demo.util;

import com.example.demo.entity.Product;
import com.example.demo.entity.Warranty;

public class ModelValidator {

    private ModelValidator() {}

    public static void validateProduct(Product product) {
        if (product.getModelNumber() == null ||
            product.getModelNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Model number required");
        }

        if (product.getCategory() == null ||
            product.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("Category required");
        }
    }

    public static void validateWarranty(Warranty warranty) {
        if (warranty.getPurchaseDate() == null ||
            warranty.getExpiryDate() == null) {
            throw new IllegalArgumentException("Dates required");
        }

        if (!warranty.getExpiryDate()
                .isAfter(warranty.getPurchaseDate())) {
            throw new IllegalArgumentException(
                    "Expiry date must be after purchase date");
        }
    }
}
