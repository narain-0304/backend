package com.examly.springapp.controller;

import com.examly.springapp.model.Venue;
import com.examly.springapp.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@CrossOrigin(origins = "*")
public class VenueController {

    @Autowired
    private VenueService venueService;

    @PostMapping
    public Venue createVenue(@RequestBody Venue venue) {
        return venueService.saveVenue(venue);
    }

    @GetMapping
    public List<Venue> getAllVenues() {
        List<Venue> venues = venueService.getAllVenues();
        // Break circular references
        venues.forEach(venue -> {
            if (venue.getMatches() != null) {
                venue.getMatches().forEach(match -> {
                    match.setVenue(null);
                });
            }
        });
        return venues;
    }

    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable Long id) {
        return venueService.getVenueById(id);
    }

    @PutMapping("/{id}")
    public Venue updateVenue(@PathVariable Long id, @RequestBody Venue venue) {
        return venueService.updateVenue(id, venue);
    }

    @DeleteMapping("/{id}")
    public void deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
    }
}