package com.ufrn.tft_stats.domain;

import jakarta.persistence.*;

@Entity
@IdClass(ChampionStatsId.class)
public class ChampionStats {

    @Id
    private String patch;
    
    @Id
    private String championId;
    
    @Id
    private Integer tier;

    private Long totalMatches = 0L;
    private Long top4Count = 0L;
    private Long winCount = 0L;
    private Long sumPlacement = 0L;

    public ChampionStats() {}

    public ChampionStats(String patch, String championId, Integer tier) {
        this.patch = patch;
        this.championId = championId;
        this.tier = tier;
    }

    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }

    public String getChampionId() { return championId; }
    public void setChampionId(String championId) { this.championId = championId; }

    public Integer getTier() { return tier; }
    public void setTier(Integer tier) { this.tier = tier; }

    public Long getTotalMatches() { return totalMatches; }
    public void setTotalMatches(Long totalMatches) { this.totalMatches = totalMatches; }

    public Long getTop4Count() { return top4Count; }
    public void setTop4Count(Long top4Count) { this.top4Count = top4Count; }

    public Long getWinCount() { return winCount; }
    public void setWinCount(Long winCount) { this.winCount = winCount; }

    public Long getSumPlacement() { return sumPlacement; }
    public void setSumPlacement(Long sumPlacement) { this.sumPlacement = sumPlacement; }
}