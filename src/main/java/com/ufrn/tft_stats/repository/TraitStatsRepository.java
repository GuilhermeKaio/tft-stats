package com.ufrn.tft_stats.repository;

import com.ufrn.tft_stats.domain.TraitStats;
import com.ufrn.tft_stats.domain.TraitStatsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitStatsRepository extends JpaRepository<TraitStats, TraitStatsId> {
}
