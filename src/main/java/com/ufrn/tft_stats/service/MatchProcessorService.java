package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.ChampionStatsId;
import com.ufrn.tft_stats.dto.ParticipantDto;
import com.ufrn.tft_stats.dto.RiotMatchDto;
import com.ufrn.tft_stats.dto.UnitDto;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchProcessorService {

    private final ChampionStatsRepository repository;

    public MatchProcessorService(ChampionStatsRepository repository) {
        this.repository = repository;
    }

    public void processMatch(RiotMatchDto matchDto) {
        String patch = extractPatchVersion(matchDto.getInfo().getGame_version());

        for (ParticipantDto participant : matchDto.getInfo().getParticipants()) {
            int placement = participant.getPlacement();
            boolean isTop4 = placement <= 4;
            boolean isWin = placement == 1;

            for (UnitDto unit : participant.getUnits()) {
                
                if (unit.getCharacter_id() == null || unit.getCharacter_id().isEmpty()) {
                    continue;
                }

                ChampionStatsId statsId = new ChampionStatsId();
                statsId.setPatch(patch);
                statsId.setChampionId(unit.getCharacter_id());
                statsId.setTier(unit.getTier());

                ChampionStats stats = repository.findById(statsId)
                        .orElse(new ChampionStats(patch, unit.getCharacter_id(), unit.getTier()));

                stats.setTotalMatches(stats.getTotalMatches() + 1);
                stats.setSumPlacement(stats.getSumPlacement() + placement);
                
                if (isTop4) {
                    stats.setTop4Count(stats.getTop4Count() + 1);
                }
                if (isWin) {
                    stats.setWinCount(stats.getWinCount() + 1);
                }

                repository.save(stats);
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
