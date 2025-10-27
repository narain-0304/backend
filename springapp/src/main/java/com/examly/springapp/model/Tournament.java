package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tournamentName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String hostCountry;
    private String status;
    private String edition;

    // Relationships
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Match> matches;

    public Tournament() {}

    // Getters and Setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getTournamentName() { 
        return tournamentName; 
    }
    public void setTournamentName(String tournamentName) { 
        this.tournamentName = tournamentName; 
    }

    public LocalDate getStartDate() { 
        return startDate; 
    }
    public void setStartDate(LocalDate startDate) { 
        this.startDate = startDate; 
    }

    public LocalDate getEndDate() { 
        return endDate; 
    }
    public void setEndDate(LocalDate endDate) { 
        this.endDate = endDate; 
    }

    public String getHostCountry() { 
        return hostCountry; 
    }
    public void setHostCountry(String hostCountry) { 
        this.hostCountry = hostCountry; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getEdition() { 
        return edition; 
    }
    public void setEdition(String edition) { 
        this.edition = edition; 
    }

    public List<Match> getMatches() { 
        return matches; 
    }
    public void setMatches(List<Match> matches) { 
        this.matches = matches; 
    }
}