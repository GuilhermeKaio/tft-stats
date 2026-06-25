package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.domain.*;
import com.ufrn.tft_stats.dto.*;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import com.ufrn.tft_stats.repository.ItemStatsRepository;
import com.ufrn.tft_stats.repository.TraitStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchProcessorService {

	private final ChampionStatsRepository championRepository;
	private final TraitStatsRepository traitRepository;
	private final ItemStatsRepository itemRepository;

	public MatchProcessorService(ChampionStatsRepository championRepository, TraitStatsRepository traitRepository,
			ItemStatsRepository itemRepository) {
		this.championRepository = championRepository;
		this.traitRepository = traitRepository;
		this.itemRepository = itemRepository;
	}

	@CacheEvict(value = { "championsMeta", "itemsMeta", "traitsMeta", "championById", "itemsByChampion", "itemsByItem",
			"traitById" }, allEntries = true)
	public void processMatch(RiotMatchDto matchDto) {
		String patch = extractPatchVersion(matchDto.getInfo().getGame_version());

		for (ParticipantDto participant : matchDto.getInfo().getParticipants()) {
			int placement = participant.getPlacement();
			boolean isTop4 = placement <= 4;
			boolean isWin = placement == 1;

			if (participant.getTraits() != null) {
				for (TraitDto trait : participant.getTraits()) {
					if (trait.getTier_current() > 0) {
						TraitStatsId traitId = new TraitStatsId();
						traitId.setPatch(patch);
						traitId.setTraitId(trait.getName());
						traitId.setTierCurrent(trait.getTier_current());

						TraitStats tStats = traitRepository.findById(traitId)
								.orElse(new TraitStats(patch, trait.getName(), trait.getTier_current()));

						tStats.setTotalMatches(tStats.getTotalMatches() + 1);
						tStats.setSumPlacement(tStats.getSumPlacement() + placement);
						if (isTop4)
							tStats.setTop4Count(tStats.getTop4Count() + 1);
						if (isWin)
							tStats.setWinCount(tStats.getWinCount() + 1);

						traitRepository.save(tStats);
					}
				}
			}

			if (participant.getUnits() != null) {
				for (UnitDto unit : participant.getUnits()) {
					if (unit.getCharacter_id() == null || unit.getCharacter_id().isEmpty()) {
						continue;
					}

					ChampionStatsId champId = new ChampionStatsId();
					champId.setPatch(patch);
					champId.setChampionId(unit.getCharacter_id());
					champId.setTier(unit.getTier());

					ChampionStats cStats = championRepository.findById(champId)
							.orElse(new ChampionStats(patch, unit.getCharacter_id(), unit.getTier()));

					cStats.setTotalMatches(cStats.getTotalMatches() + 1);
					cStats.setSumPlacement(cStats.getSumPlacement() + placement);
					if (isTop4)
						cStats.setTop4Count(cStats.getTop4Count() + 1);
					if (isWin)
						cStats.setWinCount(cStats.getWinCount() + 1);

					// ==========================================
					// NOVA LÓGICA DE COMBOS DE ITENS (JSONB)
					// ==========================================
					List<String> items = unit.getItemNames();
					if (items != null && items.size() == 3) {
						List<String> sortedItems = new ArrayList<>(items);
						Collections.sort(sortedItems);
						String comboKey = String.join(" | ", sortedItems);

						Map<String, ItemComboStats> combos = cStats.getItemCombos();
						if (combos == null) {
							combos = new HashMap<>();
							cStats.setItemCombos(combos);
						}

						ItemComboStats currentCombo = combos.getOrDefault(comboKey, new ItemComboStats(0, 0, 0));
						currentCombo.setTotalMatches(currentCombo.getTotalMatches() + 1);
						if (isWin) currentCombo.setWinCount(currentCombo.getWinCount() + 1);
						if (isTop4) currentCombo.setTop4Count(currentCombo.getTop4Count() + 1);

						combos.put(comboKey, currentCombo);
					}

					championRepository.save(cStats);

					if (unit.getItemNames() != null) {
						for (String itemName : unit.getItemNames()) {
							ItemStatsId itemId = new ItemStatsId();
							itemId.setPatch(patch);
							itemId.setChampionId(unit.getCharacter_id());
							itemId.setItemId(itemName);

							ItemStats iStats = itemRepository.findById(itemId)
									.orElse(new ItemStats(patch, unit.getCharacter_id(), itemName));

							iStats.setTotalMatches(iStats.getTotalMatches() + 1);
							iStats.setSumPlacement(iStats.getSumPlacement() + placement);
							if (isTop4)
								iStats.setTop4Count(iStats.getTop4Count() + 1);
							if (isWin)
								iStats.setWinCount(iStats.getWinCount() + 1);

							itemRepository.save(iStats);
						}
					}
				}
			}
		}
	}

	private String extractPatchVersion(String rawVersion) {
		if (rawVersion != null && rawVersion.contains(" ")) {
			String[] parts = rawVersion.split(" ");
			for (String part : parts) {
				if (part.matches("\\d+\\.\\d+.*")) {
					String[] subParts = part.split("\\.");
					if (subParts.length >= 2) {
						return subParts[0] + "." + subParts[1];
					}
				}
			}
		}
		return "Unknown";
	}
}