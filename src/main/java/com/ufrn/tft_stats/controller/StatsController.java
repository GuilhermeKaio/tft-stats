package com.ufrn.tft_stats.controller;

import com.ufrn.tft_stats.dto.ChampionComboResponseDto;
import com.ufrn.tft_stats.dto.ChampionResponseDto;
import com.ufrn.tft_stats.dto.ItemResponseDto;
import com.ufrn.tft_stats.dto.TraitResponseDto;
import com.ufrn.tft_stats.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meta")
@CrossOrigin(origins = "http://localhost:5173")
public class StatsController {

	private final StatsService statsService;

	public StatsController(StatsService statsService) {
		this.statsService = statsService;
	}
	
	@GetMapping("/champions/{championId}/combos")
	public List<ChampionComboResponseDto> getChampionCombos(
			@PathVariable String championId,
			@RequestParam(required = false) String patch) {
		return statsService.getChampionItemCombos(championId, patch);
	}

	@GetMapping("/champions")
	public List<ChampionResponseDto> getChampions(@RequestParam(required = false) String patch) {
		return statsService.getChampionsMeta(patch);
	}

	@GetMapping("/traits")
	public List<TraitResponseDto> getTraits(@RequestParam(required = false) String patch) {
		return statsService.getTraitsMeta(patch);
	}

	@GetMapping("/items")
	public List<ItemResponseDto> getItems(@RequestParam(required = false) String patch) {
		return statsService.getItemsMeta(patch);
	}

	@GetMapping("/champions/{championId}")
	public List<ChampionResponseDto> getChampionById(@PathVariable String championId,
			@RequestParam(required = false) String patch) {
		return statsService.getChampionMetaById(championId, patch);
	}

	@GetMapping("/champions/{championId}/items")
	public List<ItemResponseDto> getItemsByChampionId(@PathVariable String championId,
			@RequestParam(required = false) String patch) {
		return statsService.getItemsMetaByChampionId(championId, patch);
	}

	@GetMapping("/items/{itemId}")
	public List<ItemResponseDto> getItemsByItemId(@PathVariable String itemId,
			@RequestParam(required = false) String patch) {
		return statsService.getItemsMetaByItemId(itemId, patch);
	}

	@GetMapping("/traits/{traitId}")
	public List<TraitResponseDto> getTraitById(@PathVariable String traitId,
			@RequestParam(required = false) String patch) {
		return statsService.getTraitMetaById(traitId, patch);
	}
}