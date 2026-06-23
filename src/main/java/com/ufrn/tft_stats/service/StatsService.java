package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.TraitStats;
import com.ufrn.tft_stats.dto.ChampionResponseDto;
import com.ufrn.tft_stats.dto.TraitResponseDto;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import com.ufrn.tft_stats.repository.TraitStatsRepository;
import com.ufrn.tft_stats.domain.ItemStats;
import com.ufrn.tft_stats.dto.ItemResponseDto;
import com.ufrn.tft_stats.repository.ItemStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

	private final ChampionStatsRepository championRepository;
	private final TraitStatsRepository traitRepository;
	private final ItemStatsRepository itemRepository;

	public StatsService(ChampionStatsRepository championRepository, TraitStatsRepository traitRepository,
			ItemStatsRepository itemRepository) {
		this.championRepository = championRepository;
		this.traitRepository = traitRepository;
		this.itemRepository = itemRepository;
	}

	@Cacheable("championsMeta")
	public List<ChampionResponseDto> getChampionsMeta() {
		List<ChampionStats> allStats = championRepository.findAll();
		List<ChampionResponseDto> responseList = new ArrayList<>();

		for (ChampionStats stats : allStats) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			winRate = Math.round(winRate * 100.0) / 100.0;
			top4Rate = Math.round(top4Rate * 100.0) / 100.0;
			avgPlacement = Math.round(avgPlacement * 100.0) / 100.0;

			responseList.add(new ChampionResponseDto(stats.getPatch(), stats.getChampionId(), stats.getTier(),
					stats.getTotalMatches(), winRate, top4Rate, avgPlacement));
		}

		return responseList;
	}

	@Cacheable("traitsMeta")
	public List<TraitResponseDto> getTraitsMeta() {
		List<TraitStats> allStats = traitRepository.findAll();
		List<TraitResponseDto> responseList = new ArrayList<>();

		for (TraitStats stats : allStats) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			winRate = Math.round(winRate * 100.0) / 100.0;
			top4Rate = Math.round(top4Rate * 100.0) / 100.0;
			avgPlacement = Math.round(avgPlacement * 100.0) / 100.0;

			responseList.add(new TraitResponseDto(stats.getPatch(), stats.getTraitId(), stats.getTierCurrent(), stats.getTotalMatches(),
					winRate, top4Rate, avgPlacement));
		}
		return responseList;
	}

	@Cacheable("itemsMeta")
	public List<ItemResponseDto> getItemsMeta() {
		List<ItemStats> allStats = itemRepository.findAll();
		List<ItemResponseDto> responseList = new ArrayList<>();

		for (ItemStats stats : allStats) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			responseList.add(new ItemResponseDto(stats.getPatch(), stats.getChampionId(), stats.getItemId(), stats.getTotalMatches(),
					Math.round(winRate * 100.0) / 100.0, Math.round(top4Rate * 100.0) / 100.0,
					Math.round(avgPlacement * 100.0) / 100.0));
		}
		return responseList;
	}

	@Cacheable("championById")
	public List<ChampionResponseDto> getChampionMetaById(String championId) {
		List<ChampionStats> statsList = championRepository.findByChampionId(championId);
		List<ChampionResponseDto> responseList = new ArrayList<>();

		for (ChampionStats stats : statsList) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			responseList.add(new ChampionResponseDto(stats.getPatch(), stats.getChampionId(), stats.getTier(), stats.getTotalMatches(),
					Math.round(winRate * 100.0) / 100.0, Math.round(top4Rate * 100.0) / 100.0,
					Math.round(avgPlacement * 100.0) / 100.0));
		}

		return responseList;
	}

	@Cacheable("itemsByChampion")
	public List<ItemResponseDto> getItemsMetaByChampionId(String championId) {
		List<ItemStats> statsList = itemRepository.findByChampionId(championId);
		List<ItemResponseDto> responseList = new ArrayList<>();

		for (ItemStats stats : statsList) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			responseList.add(new ItemResponseDto(stats.getPatch(), stats.getChampionId(), stats.getItemId(), stats.getTotalMatches(),
					Math.round(winRate * 100.0) / 100.0, Math.round(top4Rate * 100.0) / 100.0,
					Math.round(avgPlacement * 100.0) / 100.0));
		}
		return responseList;
	}

	@Cacheable("itemsByItem")
	public List<ItemResponseDto> getItemsMetaByItemId(String itemId) {
		List<ItemStats> statsList = itemRepository.findByItemId(itemId);
		List<ItemResponseDto> responseList = new ArrayList<>();

		for (ItemStats stats : statsList) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			responseList.add(new ItemResponseDto(stats.getPatch(), stats.getChampionId(), stats.getItemId(), stats.getTotalMatches(),
					Math.round(winRate * 100.0) / 100.0, Math.round(top4Rate * 100.0) / 100.0,
					Math.round(avgPlacement * 100.0) / 100.0));
		}
		return responseList;
	}

	@Cacheable("traitById")
	public List<TraitResponseDto> getTraitMetaById(String traitId) {
		List<TraitStats> statsList = traitRepository.findByTraitId(traitId);
		List<TraitResponseDto> responseList = new ArrayList<>();

		for (TraitStats stats : statsList) {
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
			double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
			double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

			responseList.add(new TraitResponseDto(stats.getPatch(), stats.getTraitId(), stats.getTierCurrent(), stats.getTotalMatches(),
					Math.round(winRate * 100.0) / 100.0, Math.round(top4Rate * 100.0) / 100.0,
					Math.round(avgPlacement * 100.0) / 100.0));
		}

		return responseList;
	}
}