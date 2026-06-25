package com.ufrn.tft_stats.domain;

public class ItemComboStats {
    private int totalMatches;
    private int winCount;
    private int top4Count;

    public ItemComboStats() {}

    public ItemComboStats(int totalMatches, int winCount, int top4Count) {
        this.totalMatches = totalMatches;
        this.winCount = winCount;
        this.top4Count = top4Count;
    }

    public int getTotalMatches() { return totalMatches; }
    public void setTotalMatches(int totalMatches) { this.totalMatches = totalMatches; }

    public int getWinCount() { return winCount; }
    public void setWinCount(int winCount) { this.winCount = winCount; }

    public int getTop4Count() { return top4Count; }
    public void setTop4Count(int top4Count) { this.top4Count = top4Count; }
}