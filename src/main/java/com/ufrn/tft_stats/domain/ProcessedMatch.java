package com.ufrn.tft_stats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcessedMatch {

    @Id
    private String matchId;

    public ProcessedMatch() {}

    public ProcessedMatch(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() { return matchId; }
    public void setMatchId(String matchId) { this.matchId = matchId; }
}