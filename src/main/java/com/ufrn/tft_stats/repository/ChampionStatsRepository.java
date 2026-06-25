package com.ufrn.tft_stats.repository;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.ChampionStatsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChampionStatsRepository extends JpaRepository<ChampionStats, ChampionStatsId> {
	List<ChampionStats> findByPatch(String patch);

	List<ChampionStats> findByChampionIdAndPatch(String championId, String patch);

	List<ChampionStats> findByChampionId(String championId);
}