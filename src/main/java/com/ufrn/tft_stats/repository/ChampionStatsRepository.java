package com.ufrn.tft_stats.repository;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.ChampionStatsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionStatsRepository extends JpaRepository<ChampionStats, ChampionStatsId> {
    
}