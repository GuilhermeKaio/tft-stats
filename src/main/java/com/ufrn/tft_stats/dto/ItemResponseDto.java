package com.ufrn.tft_stats.dto;

public class ItemResponseDto {
    private String championId;
    private String itemId;
    private Long totalMatches;
    private Double winRate;
    private Double top4Rate;
    private Double avgPlacement;

    public ItemResponseDto(String championId, String itemId, Long totalMatches, Double winRate, Double top4Rate, Double avgPlacement) {
        this.championId = championId;
        this.itemId = itemId;
        this.totalMatches = totalMatches;
        this.winRate = winRate;
        this.top4Rate = top4Rate;
        this.avgPlacement = avgPlacement;
    }

    // Getters
    public String getChampionId() { return championId; }
    public String getItemId() { return itemId; }
    public Long getTotalMatches() { return totalMatches; }
    public Double getWinRate() { return winRate; }
    public Double getTop4Rate() { return top4Rate; }
    public Double getAvgPlacement() { return avgPlacement; }
}