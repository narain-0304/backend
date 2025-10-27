package com.examly.springapp.service;

import com.examly.springapp.model.Player;
import com.examly.springapp.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id " + id));
    }

    public Player updatePlayer(Long id, Player playerDetails) {
        Player player = getPlayerById(id);
        player.setPosition(playerDetails.getPosition());
        player.setTeam(playerDetails.getTeam());
        player.setNationality(playerDetails.getNationality());
        player.setJerseyNumber(playerDetails.getJerseyNumber());
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    // ✅ NEW: Pagination & Sorting Method
    public Page<Player> getPlayersPaginated(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return playerRepository.findAll(pageable);
    }
}