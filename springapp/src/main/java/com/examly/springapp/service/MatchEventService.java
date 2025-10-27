package com.examly.springapp.service;

import com.examly.springapp.model.MatchEvent;
import com.examly.springapp.repository.MatchEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchEventService {

    @Autowired
    private MatchEventRepository matchEventRepository;

    public MatchEvent saveMatchEvent(MatchEvent event) {
        return matchEventRepository.save(event);
    }

    public List<MatchEvent> getAllMatchEvents() {
        return matchEventRepository.findAll();
    }

    public MatchEvent getMatchEventById(Long id) {
        return matchEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MatchEvent not found with id " + id));
    }

    public MatchEvent updateMatchEvent(Long id, MatchEvent eventDetails) {
        MatchEvent event = getMatchEventById(id);
        event.setMatch(eventDetails.getMatch());
        event.setPlayer(eventDetails.getPlayer());
        event.setEventType(eventDetails.getEventType());
        return matchEventRepository.save(event);
    }

    public void deleteMatchEvent(Long id) {
        matchEventRepository.deleteById(id);
    }
}