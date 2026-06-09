package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.TraitStats;
import com.ufrn.tft_stats.dto.ChampionResponseDto;
import com.ufrn.tft_stats.dto.TraitResponseDto;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import com.ufrn.tft_stats.repository.TraitStatsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    private final ChampionStatsRepository championRepository;
    private final TraitStatsRepository traitRepository;

    public StatsService(ChampionStatsRepository championRepository, TraitStatsRepository traitRepository) {
        this.championRepository = championRepository;
        this.traitRepository = traitRepository;
    }

    public List<ChampionResponseDto> getChampionsMeta() {
        List<ChampionStats> allStats = championRepository.findAll();
        List<ChampionResponseDto> responseList = new ArrayList<>();

        for (ChampionStats stats : allStats) {
            if (stats.getTotalMatches() == 0) continue;

            double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
            double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
            double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

            winRate = Math.round(winRate * 100.0) / 100.0;
            top4Rate = Math.round(top4Rate * 100.0) / 100.0;
            avgPlacement = Math.round(avgPlacement * 100.0) / 100.0;

            responseList.add(new ChampionResponseDto(
                    stats.getChampionId(),
                    stats.getTier(),
                    stats.getTotalMatches(),
                    winRate,
                    top4Rate,
                    avgPlacement
            ));
        }

        return responseList;
    }
    
    public List<TraitResponseDto> getTraitsMeta() {
        List<TraitStats> allStats = traitRepository.findAll();
        List<TraitResponseDto> responseList = new ArrayList<>();

        for (TraitStats stats : allStats) {
            if (stats.getTotalMatches() == 0) continue;

            double winRate = ((double) stats.getWinCount() / stats.getTotalMatches()) * 100;
            double top4Rate = ((double) stats.getTop4Count() / stats.getTotalMatches()) * 100;
            double avgPlacement = (double) stats.getSumPlacement() / stats.getTotalMatches();

            winRate = Math.round(winRate * 100.0) / 100.0;
            top4Rate = Math.round(top4Rate * 100.0) / 100.0;
            avgPlacement = Math.round(avgPlacement * 100.0) / 100.0;

            responseList.add(new TraitResponseDto(
                    stats.getTraitId(),
                    stats.getTierCurrent(),
                    stats.getTotalMatches(),
                    winRate,
                    top4Rate,
                    avgPlacement
            ));
        }
        return responseList;
    }
}