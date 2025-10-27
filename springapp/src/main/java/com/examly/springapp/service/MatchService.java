package com.examly.springapp.service;

import com.examly.springapp.model.Match;
import com.examly.springapp.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found with id " + id));
    }

    public Match updateMatch(Long id, Match matchDetails) {
        Match match = getMatchById(id);
        match.setMatchDate(matchDetails.getMatchDate());
        match.setKickoffTime(matchDetails.getKickoffTime());
        match.setVenue(matchDetails.getVenue());
        match.setHomeTeam(matchDetails.getHomeTeam());
        match.setAwayTeam(matchDetails.getAwayTeam());
        match.setTournament(matchDetails.getTournament());
        match.setStatus(matchDetails.getStatus());
        match.setHomeScore(matchDetails.getHomeScore());
        match.setAwayScore(matchDetails.getAwayScore());
        match.setRound(matchDetails.getRound());
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}