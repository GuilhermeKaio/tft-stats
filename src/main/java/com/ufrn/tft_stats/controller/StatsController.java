package com.ufrn.tft_stats.controller;

import com.ufrn.tft_stats.dto.ChampionResponseDto;
import com.ufrn.tft_stats.dto.TraitResponseDto;
import com.ufrn.tft_stats.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meta")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/champions")
    public List<ChampionResponseDto> getChampions() {
        return statsService.getChampionsMeta();
    }
    
    @GetMapping("/traits")
    public List<TraitResponseDto> getTraits() {
        return statsService.getTraitsMeta();
    }
}