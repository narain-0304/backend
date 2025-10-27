package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    // store BCrypt hashed password here
    private String passwordHash;

    private String role;
    private String fifaId;
    private String confederation;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;
    private boolean isActive;

    // Avoid infinite recursion when serializing
    @OneToMany(mappedBy = "teamManager", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Team> managedTeams;

    public User() {}

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFifaId() { return fifaId; }
    public void setFifaId(String fifaId) { this.fifaId = fifaId; }
    public String getConfederation() { return confederation; }
    public void setConfederation(String confederation) { this.confederation = confederation; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    public boolean isActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }
    public List<Team> getManagedTeams() { return managedTeams; }
    public void setManagedTeams(List<Team> managedTeams) { this.managedTeams = managedTeams; }
}