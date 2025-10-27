package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private String confederation;
    private int fifaRanking;
    private String headCoach;
    private LocalDate registrationDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "team_manager_id")
    @JsonIgnore
    private User teamManager;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonManagedReference  // ✅ Prevents infinite recursion
    private List<Player> players;

    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL)
    @JsonIgnore
    private TeamStats teamStats;

    public Team() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getConfederation() { return confederation; }
    public void setConfederation(String confederation) { this.confederation = confederation; }

    public int getFifaRanking() { return fifaRanking; }
    public void setFifaRanking(int fifaRanking) { this.fifaRanking = fifaRanking; }

    public String getHeadCoach() { return headCoach; }
    public void setHeadCoach(String headCoach) { this.headCoach = headCoach; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getTeamManager() { return teamManager; }
    public void setTeamManager(User teamManager) { this.teamManager = teamManager; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public TeamStats getTeamStats() { return teamStats; }
    public void setTeamStats(TeamStats teamStats) { this.teamStats = teamStats; }
}