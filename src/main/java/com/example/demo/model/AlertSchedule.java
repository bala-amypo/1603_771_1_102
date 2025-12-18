package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alert_schedules")
public class AlertSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Warranty warranty;

    private Integer daysBeforeExpiry;
    private Boolean enabled = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Warranty getWarranty() { return warranty; }
    public void setWarranty(Warranty warranty) { this.warranty = warranty; }

    public Integer getDaysBeforeExpiry() { return daysBeforeExpiry; }
    public void setDaysBeforeExpiry(Integer daysBeforeExpiry) { this.daysBeforeExpiry = daysBeforeExpiry; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}
