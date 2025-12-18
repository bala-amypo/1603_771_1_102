@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password; // Should be stored as a hash

    private String role = "USER"; // Default role

    // Standard Getters and Setters
}