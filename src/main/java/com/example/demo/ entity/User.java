package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Warranty> warranties;

    // REQUIRED by JPA
    public User() {
    }

    // REQUIRED by tests (constructor usage)
    public User(Long id,
                String name,
                String email,
                String password,
                String role) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Full constructor
    public User(Long id,
                String name,
                String email,
                String password,
                String role,
                List<Warranty> warranties) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.warranties = warranties;
    }
}
