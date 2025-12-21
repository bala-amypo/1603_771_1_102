package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AlertSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Warranty warranty;

    private Integer daysBeforeExpiry;
    private Boolean enabled;

    public AlertSchedule() {}

    public Long getId() {
        return id;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public Integer getDaysBeforeExpiry() {
        return daysBeforeExpiry;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public void setDaysBeforeExpiry(Integer daysBeforeExpiry) {
        this.daysBeforeExpiry = daysBeforeExpiry;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
