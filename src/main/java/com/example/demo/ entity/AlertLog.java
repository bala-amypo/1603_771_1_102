package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_logs")
public class AlertLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warranty_id")
    private Warranty warranty;

    private LocalDateTime sentAt;

    private String message;

    @PrePersist
    public void onCreate() {
        this.sentAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Warranty getWarranty() { return warranty; }
    public void setWarranty(Warranty warranty) { this.warranty = warranty; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
