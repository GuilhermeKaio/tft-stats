package com.ufrn.tft_stats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProcessedMatch {

    @Id
    private String matchId;

    private String status;

    public ProcessedMatch() {}

    public ProcessedMatch(String matchId, String status) {
        this.matchId = matchId;
        this.status = status;
    }

    public String getMatchId() { return matchId; }
    public void setMatchId(String matchId) { this.matchId = matchId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}