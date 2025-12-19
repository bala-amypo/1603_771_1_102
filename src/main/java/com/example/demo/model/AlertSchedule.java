import jakarta.persistence.*;

@Entity
public class AlertSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warranty_id")
    private Warranty warranty;

    private Integer daysBeforeExpiry;

    private Boolean enabled = true;

    @PrePersist
    @PreUpdate
    private void checkDays() {
        if (daysBeforeExpiry != null && daysBeforeExpiry < 0) {
            throw new RuntimeException("daysBeforeExpiry must be >= 0");
        }
    }
}