package com.ufrn.tft_stats.domain;

import jakarta.persistence.*;

@Entity
@IdClass(ItemStatsId.class)
public class ItemStats {

    @Id private String patch;
    @Id private String championId;
    @Id private String itemId;

    private Long totalMatches = 0L;
    private Long top4Count = 0L;
    private Long winCount = 0L;
    private Long sumPlacement = 0L;

    public ItemStats() {}

    public ItemStats(String patch, String championId, String itemId) {
        this.patch = patch;
        this.championId = championId;
        this.itemId = itemId;
    }

    // Getters e Setters
    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }
    public String getChampionId() { return championId; }
    public void setChampionId(String championId) { this.championId = championId; }
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    public Long getTotalMatches() { return totalMatches; }
    public void setTotalMatches(Long totalMatches) { this.totalMatches = totalMatches; }
    public Long getTop4Count() { return top4Count; }
    public void setTop4Count(Long top4Count) { this.top4Count = top4Count; }
    public Long getWinCount() { return winCount; }
    public void setWinCount(Long winCount) { this.winCount = winCount; }
    public Long getSumPlacement() { return sumPlacement; }
    public void setSumPlacement(Long sumPlacement) { this.sumPlacement = sumPlacement; }
}