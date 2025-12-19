import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String modelNumber;
    private String category;

    @PrePersist
    @PreUpdate
    private void validate() {
        if (this.modelNumber == null || this.modelNumber.isBlank()) {
            throw new RuntimeException("Model number required");
        }
        if (this.category == null || this.category.isBlank()) {
            throw new RuntimeException("Category required");
        }
    }
}