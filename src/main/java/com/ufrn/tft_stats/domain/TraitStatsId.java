package com.ufrn.tft_stats.domain;

import java.io.Serializable;
import java.util.Objects;

public class TraitStatsId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String patch;
    private String traitId;
    private Integer tierCurrent;

    public TraitStatsId() {}

    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }

    public String getTraitId() { return traitId; }
    public void setTraitId(String traitId) { this.traitId = traitId; }

    public Integer getTierCurrent() { return tierCurrent; }
    public void setTierCurrent(Integer tierCurrent) { this.tierCurrent = tierCurrent; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraitStatsId that = (TraitStatsId) o;
        return Objects.equals(patch, that.patch) &&
               Objects.equals(traitId, that.traitId) &&
               Objects.equals(tierCurrent, that.tierCurrent);
    }

    @Override
    public int hashCode() { return Objects.hash(patch, traitId, tierCurrent); }
}
