package com.ufrn.tft_stats.repository;

import com.ufrn.tft_stats.domain.ItemStats;
import com.ufrn.tft_stats.domain.ItemStatsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemStatsRepository extends JpaRepository<ItemStats, ItemStatsId> {

	List<ItemStats> findByPatch(String patch);

	List<ItemStats> findByChampionId(String championId);

	List<ItemStats> findByChampionIdAndPatch(String championId, String patch);

	List<ItemStats> findByItemId(String itemId);

	List<ItemStats> findByItemIdAndPatch(String itemId, String patch);
}