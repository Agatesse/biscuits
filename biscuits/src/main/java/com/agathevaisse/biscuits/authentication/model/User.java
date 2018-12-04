package com.agathevaisse.biscuits.authentication.model;

import org.hibernate.annotations.NaturalId;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "biscuits_user")

public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_username")
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "user_email")
    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "user_password")
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Column(name = "user_role",length = 60)
    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName role;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRole() { return role; }

    public void setRole(RoleName role) { this.role = role; }
}
