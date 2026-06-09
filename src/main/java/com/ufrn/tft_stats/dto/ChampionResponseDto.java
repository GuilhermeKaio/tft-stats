package com.ufrn.tft_stats.dto;

public class ChampionResponseDto {
    private String championId;
    private Integer tier;
    private Long totalMatches;
    private Double winRate;
    private Double top4Rate;
    private Double avgPlacement;

    // Construtor
    public ChampionResponseDto(String championId, Integer tier, Long totalMatches, Double winRate, Double top4Rate, Double avgPlacement) {
        this.championId = championId;
        this.tier = tier;
        this.totalMatches = totalMatches;
        this.winRate = winRate;
        this.top4Rate = top4Rate;
        this.avgPlacement = avgPlacement;
    }

    // Getters
    public String getChampionId() { return championId; }
    public Integer getTier() { return tier; }
    public Long getTotalMatches() { return totalMatches; }
    public Double getWinRate() { return winRate; }
    public Double getTop4Rate() { return top4Rate; }
    public Double getAvgPlacement() { return avgPlacement; }
}