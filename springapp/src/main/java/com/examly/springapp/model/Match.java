package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    @JsonIgnoreProperties({"matches"})
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    @JsonIgnoreProperties({"matches"})
    private Venue venue;

    private LocalDate matchDate;
    private LocalTime kickoffTime;
    private String status;
    private int homeScore;
    private int awayScore;
    private String round;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<MatchEvent> events;

    public Match() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }

    public Venue getVenue() { return venue; }
    public void setVenue(Venue venue) { this.venue = venue; }

    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }

    public LocalTime getKickoffTime() { return kickoffTime; }
    public void setKickoffTime(LocalTime kickoffTime) { this.kickoffTime = kickoffTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public int getAwayScore() { return awayScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }

    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }

    public List<MatchEvent> getEvents() { return events; }
    public void setEvents(List<MatchEvent> events) { this.events = events; }
}