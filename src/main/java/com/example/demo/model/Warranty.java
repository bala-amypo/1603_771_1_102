import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDate purchaseDate;
    private LocalDate expiryDate;

    @Column(unique = true)
    private String serialNumber;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (expiryDate != null && purchaseDate != null) {
            if (!expiryDate.isAfter(purchaseDate)) {
                throw new RuntimeException("Expiry date must be after purchase date.");
            }
        }
    }
}