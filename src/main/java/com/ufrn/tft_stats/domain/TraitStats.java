package com.ufrn.tft_stats.domain;

import jakarta.persistence.*;

@Entity
@IdClass(TraitStatsId.class)
public class TraitStats {

    @Id
    private String patch;
    
    @Id
    private String traitId;
    
    @Id
    private Integer tierCurrent;

    private Long totalMatches = 0L;
    private Long top4Count = 0L;
    private Long winCount = 0L;
    private Long sumPlacement = 0L;

    public TraitStats() {}

    public TraitStats(String patch, String traitId, Integer tierCurrent) {
        this.patch = patch;
        this.traitId = traitId;
        this.tierCurrent = tierCurrent;
    }

    // Getters e Setters
    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }
    public String getTraitId() { return traitId; }
    public void setTraitId(String traitId) { this.traitId = traitId; }
    public Integer getTierCurrent() { return tierCurrent; }
    public void setTierCurrent(Integer tierCurrent) { this.tierCurrent = tierCurrent; }
    public Long getTotalMatches() { return totalMatches; }
    public void setTotalMatches(Long totalMatches) { this.totalMatches = totalMatches; }
    public Long getTop4Count() { return top4Count; }
    public void setTop4Count(Long top4Count) { this.top4Count = top4Count; }
    public Long getWinCount() { return winCount; }
    public void setWinCount(Long winCount) { this.winCount = winCount; }
    public Long getSumPlacement() { return sumPlacement; }
    public void setSumPlacement(Long sumPlacement) { this.sumPlacement = sumPlacement; }
}
