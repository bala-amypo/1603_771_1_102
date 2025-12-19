import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AlertLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warranty_id")
    private Warranty warranty;

    private LocalDateTime sentAt;

    @Column(columnDefinition = "TEXT")
    private String message;

    @PrePersist
    protected void onSent() {
        this.sentAt = LocalDateTime.now(); // Auto-generated
    }
}