package com.ufrn.tft_stats.domain;

import java.io.Serializable;
import java.util.Objects;

public class ItemStatsId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String patch;
    private String championId;
    private String itemId;

    public ItemStatsId() {}

    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }

    public String getChampionId() { return championId; }
    public void setChampionId(String championId) { this.championId = championId; }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStatsId that = (ItemStatsId) o;
        return Objects.equals(patch, that.patch) &&
               Objects.equals(championId, that.championId) &&
               Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() { return Objects.hash(patch, championId, itemId); }
}