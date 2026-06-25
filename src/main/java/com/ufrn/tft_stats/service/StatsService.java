package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.TraitStats;
import com.ufrn.tft_stats.domain.ItemStats;
import com.ufrn.tft_stats.dto.ChampionResponseDto;
import com.ufrn.tft_stats.dto.TraitResponseDto;
import com.ufrn.tft_stats.dto.ItemResponseDto;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import com.ufrn.tft_stats.repository.TraitStatsRepository;
import com.ufrn.tft_stats.repository.ItemStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import com.ufrn.tft_stats.dto.ChampionComboResponseDto;
import com.ufrn.tft_stats.domain.ItemComboStats;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
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

	@Cacheable("championCombos")
	public List<ChampionComboResponseDto> getChampionItemCombos(String championId, String patch) {
		List<ChampionStats> statsList;

		if (patch != null && !patch.isBlank()) {
			statsList = championRepository.findByChampionIdAndPatch(championId, patch);
		} else {
			statsList = championRepository.findByChampionId(championId);
		}

		Map<String, ItemComboStats> aggregatedCombos = new HashMap<>();

		for (ChampionStats champStats : statsList) {
			Map<String, ItemComboStats> combos = champStats.getItemCombos();
			if (combos != null) {
				combos.forEach((comboKey, comboData) -> {
					ItemComboStats existing = aggregatedCombos.getOrDefault(comboKey, new ItemComboStats(0, 0, 0));
					existing.setTotalMatches(existing.getTotalMatches() + comboData.getTotalMatches());
					existing.setWinCount(existing.getWinCount() + comboData.getWinCount());
					existing.setTop4Count(existing.getTop4Count() + comboData.getTop4Count());
					aggregatedCombos.put(comboKey, existing);
				});
			}
		}

		List<ChampionComboResponseDto> responseList = new ArrayList<>();

		for (Map.Entry<String, ItemComboStats> entry : aggregatedCombos.entrySet()) {
			ItemComboStats stats = entry.getValue();
			if (stats.getTotalMatches() == 0)
				continue;

			double winRate = Math.round((((double) stats.getWinCount() / stats.getTotalMatches()) * 100) * 100.0)
					/ 100.0;
			double top4Rate = Math.round((((double) stats.getTop4Count() / stats.getTotalMatches()) * 100) * 100.0)
					/ 100.0;

			List<String> itemsList = Arrays.stream(entry.getKey().split(" \\| ")).collect(Collectors.toList());

			responseList.add(new ChampionComboResponseDto(itemsList, stats.getTotalMatches(), winRate, top4Rate));
		}

		responseList.sort((a, b) -> Integer.compare(b.getTotalMatches(), a.getTotalMatches()));

		return responseList;
	}

	@Cacheable("championsMeta")
	public List<ChampionResponseDto> getChampionsMeta(String patch) {
		List<ChampionStats> allStats;
		if (patch != null && !patch.isBlank()) {
			allStats = championRepository.findByPatch(patch);
		} else {
			allStats = championRepository.findAll();
		}

		List<ChampionResponseDto> responseList = new ArrayList<>();
		for (ChampionStats stats : allStats) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToChampionDto(stats));
			}
		}
		return responseList;
	}

	@Cacheable("traitsMeta")
	public List<TraitResponseDto> getTraitsMeta(String patch) {
		List<TraitStats> allStats;
		if (patch != null && !patch.isBlank()) {
			allStats = traitRepository.findByPatch(patch);
		} else {
			allStats = traitRepository.findAll();
		}

		List<TraitResponseDto> responseList = new ArrayList<>();
		for (TraitStats stats : allStats) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToTraitDto(stats));
			}
		}
		return responseList;
	}

	@Cacheable("itemsMeta")
	public List<ItemResponseDto> getItemsMeta(String patch) {
		List<ItemStats> allStats;
		if (patch != null && !patch.isBlank()) {
			allStats = itemRepository.findByPatch(patch);
		} else {
			allStats = itemRepository.findAll();
		}

		List<ItemResponseDto> responseList = new ArrayList<>();
		for (ItemStats stats : allStats) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToItemDto(stats));
			}
		}
		return responseList;
	}

	@Cacheable("championById")
	public List<ChampionResponseDto> getChampionMetaById(String championId, String patch) {
		List<ChampionStats> statsList;
		if (patch != null && !patch.isBlank()) {
			statsList = championRepository.findByChampionIdAndPatch(championId, patch);
		} else {
			statsList = championRepository.findByChampionId(championId);
		}

		List<ChampionResponseDto> responseList = new ArrayList<>();
		for (ChampionStats stats : statsList) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToChampionDto(stats));
			}
		}
		return responseList;
	}

	@Cacheable("itemsByChampion")
	public List<ItemResponseDto> getItemsMetaByChampionId(String championId, String patch) {
		List<ItemStats> statsList;
		if (patch != null && !patch.isBlank()) {
			statsList = itemRepository.findByChampionIdAndPatch(championId, patch);
		} else {
			statsList = itemRepository.findByChampionId(championId);
		}

		List<ItemResponseDto> responseList = new ArrayList<>();
		for (ItemStats stats : statsList) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToItemDto(stats));
			}
		}
		return responseList;
	}

	@Cacheable("itemsByItem")
	public List<ItemResponseDto> getItemsMetaByItemId(String itemId, String patch) {
		List<ItemStats> statsList;
		if (patch != null && !patch.isBlank()) {
			statsList = itemRepository.findByItemIdAndPatch(itemId, patch);
		} else {
			statsList = itemRepository.findByItemId(itemId);
		}

		List<ItemResponseDto> responseList = new ArrayList<>();
		for (ItemStats stats : statsList) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToItemDto(stats));
			}
		}
		return responseList;
	}

	@Cacheable("traitById")
	public List<TraitResponseDto> getTraitMetaById(String traitId, String patch) {
		List<TraitStats> statsList;
		if (patch != null && !patch.isBlank()) {
			statsList = traitRepository.findByTraitIdAndPatch(traitId, patch);
		} else {
			statsList = traitRepository.findByTraitId(traitId);
		}

		List<TraitResponseDto> responseList = new ArrayList<>();
		for (TraitStats stats : statsList) {
			if (stats.getTotalMatches() > 0) {
				responseList.add(mapToTraitDto(stats));
			}
		}
		return responseList;
	}

	private ChampionResponseDto mapToChampionDto(ChampionStats stats) {
		double winRate = Math.round((((double) stats.getWinCount() / stats.getTotalMatches()) * 100) * 100.0) / 100.0;
		double top4Rate = Math.round((((double) stats.getTop4Count() / stats.getTotalMatches()) * 100) * 100.0) / 100.0;
		double avgPlacement = Math.round(((double) stats.getSumPlacement() / stats.getTotalMatches()) * 100.0) / 100.0;

		return new ChampionResponseDto(stats.getPatch(), stats.getChampionId(), stats.getTier(),
				stats.getTotalMatches(), winRate, top4Rate, avgPlacement);
	}

	private TraitResponseDto mapToTraitDto(TraitStats stats) {
		double winRate = Math.round((((double) stats.getWinCount() / stats.getTotalMatches()) * 100) * 100.0) / 100.0;
		double top4Rate = Math.round((((double) stats.getTop4Count() / stats.getTotalMatches()) * 100) * 100.0) / 100.0;
		double avgPlacement = Math.round(((double) stats.getSumPlacement() / stats.getTotalMatches()) * 100.0) / 100.0;

		return new TraitResponseDto(stats.getPatch(), stats.getTraitId(), stats.getTierCurrent(),
				stats.getTotalMatches(), winRate, top4Rate, avgPlacement);
	}

	private ItemResponseDto mapToItemDto(ItemStats stats) {
		double winRate = Math.round((((double) stats.getWinCount() / stats.getTotalMatches()) * 100) * 100.0) / 100.0;
		double top4Rate = Math.round((((double) stats.getTop4Count() / stats.getTotalMatches()) * 100) * 100.0) / 100.0;
		double avgPlacement = Math.round(((double) stats.getSumPlacement() / stats.getTotalMatches()) * 100.0) / 100.0;

		return new ItemResponseDto(stats.getPatch(), stats.getChampionId(), stats.getItemId(), stats.getTotalMatches(),
				winRate, top4Rate, avgPlacement);
	}
}