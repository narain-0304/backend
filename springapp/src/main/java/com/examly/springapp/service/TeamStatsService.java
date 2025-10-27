package com.examly.springapp.service;

import com.examly.springapp.model.TeamStats;
import com.examly.springapp.repository.TeamStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamStatsService {

    @Autowired
    private TeamStatsRepository teamStatsRepository;

    public TeamStats saveTeamStats(TeamStats stats) {
        return teamStatsRepository.save(stats);
    }

    public List<TeamStats> getAllTeamStats() {
        return teamStatsRepository.findAll();
    }

    public TeamStats getTeamStatsById(Long id) {
        return teamStatsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TeamStats not found with id " + id));
    }

    public TeamStats updateTeamStats(Long id, TeamStats statsDetails) {
        TeamStats stats = getTeamStatsById(id);
        stats.setTeam(statsDetails.getTeam());
        stats.setMatchesPlayed(statsDetails.getMatchesPlayed());
        stats.setWins(statsDetails.getWins());
        stats.setLosses(statsDetails.getLosses());
        stats.setPoints(statsDetails.getPoints());
        return teamStatsRepository.save(stats);
    }

    public void deleteTeamStats(Long id) {
        teamStatsRepository.deleteById(id);
    }
}