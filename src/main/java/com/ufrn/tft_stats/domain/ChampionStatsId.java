package com.ufrn.tft_stats.domain;

import java.io.Serializable;
import java.util.Objects;

public class ChampionStatsId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String patch;
    private String championId;
    private Integer tier;

    public ChampionStatsId() {}

    // Getters e Setters
    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }

    public String getChampionId() { return championId; }
    public void setChampionId(String championId) { this.championId = championId; }

    public Integer getTier() { return tier; }
    public void setTier(Integer tier) { this.tier = tier; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChampionStatsId that = (ChampionStatsId) o;
        return Objects.equals(patch, that.patch) &&
               Objects.equals(championId, that.championId) &&
               Objects.equals(tier, that.tier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patch, championId, tier);
    }
}