package com.examly.springapp.controller;

import com.examly.springapp.model.Tournament;
import com.examly.springapp.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@CrossOrigin(origins = "*")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.saveTournament(tournament);
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.getAllTournaments();
        // Break circular references
        tournaments.forEach(tournament -> {
            if (tournament.getMatches() != null) {
                tournament.getMatches().forEach(match -> {
                    match.setTournament(null);
                });
            }
        });
        return tournaments;
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    @DeleteMapping("/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }
}