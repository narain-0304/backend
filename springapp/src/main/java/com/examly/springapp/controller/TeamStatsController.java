package com.examly.springapp.controller;

import com.examly.springapp.model.TeamStats;
import com.examly.springapp.service.TeamStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/teamstats")
public class TeamStatsController {

    @Autowired
    private TeamStatsService teamStatsService;

    @PostMapping
    public TeamStats createTeamStats(@RequestBody TeamStats stats) {
        return teamStatsService.saveTeamStats(stats);
    }

    @GetMapping
    public List<TeamStats> getAllTeamStats() {
        return teamStatsService.getAllTeamStats();
    }

    @GetMapping("/{id}")
    public TeamStats getTeamStatsById(@PathVariable Long id) {
        return teamStatsService.getTeamStatsById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeamStats(@PathVariable Long id) {
        teamStatsService.deleteTeamStats(id);
    }
}