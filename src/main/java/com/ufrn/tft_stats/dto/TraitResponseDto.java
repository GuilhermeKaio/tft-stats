package com.ufrn.tft_stats.dto;

public class TraitResponseDto {
	private String patch;
    private String traitId;
    private Integer tierCurrent;
    private Long totalMatches;
    private Double winRate;
    private Double top4Rate;
    private Double avgPlacement;

    public TraitResponseDto(String patch, String traitId, Integer tierCurrent, Long totalMatches, Double winRate, Double top4Rate, Double avgPlacement) {
    	this.patch = patch;
    	this.traitId = traitId;
        this.tierCurrent = tierCurrent;
        this.totalMatches = totalMatches;
        this.winRate = winRate;
        this.top4Rate = top4Rate;
        this.avgPlacement = avgPlacement;
    }

    public String getTraitId() { return traitId; }
    public Integer getTierCurrent() { return tierCurrent; }
    public Long getTotalMatches() { return totalMatches; }
    public Double getWinRate() { return winRate; }
    public Double getTop4Rate() { return top4Rate; }
    public Double getAvgPlacement() { return avgPlacement; }
    public String getPatch() { return patch; }
}