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
        if (modelNumber == null || modelNumber.isEmpty()) {
            throw new RuntimeException("Model number required");
        }
        if (category == null || category.isEmpty()) {
            throw new RuntimeException("Category required");
        }
    }
}