import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String role = "USER"; // Default role

    // Business Rule: Throw exception containing "email" if duplicate
    // This is handled by the DB unique constraint and caught in the Service layer.
}