package com.examly.springapp.repository;

import com.examly.springapp.model.TeamStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamStatsRepository extends JpaRepository<TeamStats, Long> {
    TeamStats findByTeamId(Long teamId);
}