package com.examly.springapp.controller;

import com.examly.springapp.model.MatchEvent;
import com.examly.springapp.service.MatchEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-events")
public class MatchEventController {

    @Autowired
    private MatchEventService matchEventService;

    @PostMapping
    public MatchEvent createMatchEvent(@RequestBody MatchEvent event) {
        return matchEventService.saveMatchEvent(event);
    }

    @GetMapping
    public List<MatchEvent> getAllMatchEvents() {
        return matchEventService.getAllMatchEvents();
    }

    @GetMapping("/{id}")
    public MatchEvent getMatchEventById(@PathVariable Long id) {
        return matchEventService.getMatchEventById(id);
    }

    @PutMapping("/{id}")
    public MatchEvent updateMatchEvent(@PathVariable Long id, @RequestBody MatchEvent event) {
        return matchEventService.updateMatchEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteMatchEvent(@PathVariable Long id) {
        matchEventService.deleteMatchEvent(id);
    }
}