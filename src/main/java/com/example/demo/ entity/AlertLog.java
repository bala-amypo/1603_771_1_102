package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AlertLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Warranty warranty;

    private LocalDateTime sentAt;
    private String message;

    @PrePersist
    public void onCreate() {
        sentAt = LocalDateTime.now();
    }

    public AlertLog() {}

    public Long getId() {
        return id;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public String getMessage() {
        return message;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
