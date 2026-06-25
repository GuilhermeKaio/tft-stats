package com.ufrn.tft_stats.dto;

import java.util.List;

public class ChampionComboResponseDto {

    private List<String> items;
    private int totalMatches;
    private double winRate;
    private double top4Rate;

    public ChampionComboResponseDto(List<String> items, int totalMatches, double winRate, double top4Rate) {
        this.items = items;
        this.totalMatches = totalMatches;
        this.winRate = winRate;
        this.top4Rate = top4Rate;
    }

    public List<String> getItems() { return items; }
    public int getTotalMatches() { return totalMatches; }
    public double getWinRate() { return winRate; }
    public double getTop4Rate() { return top4Rate; }
}