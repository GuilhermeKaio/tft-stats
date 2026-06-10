package com.ufrn.tft_stats.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueItemDto {
    private String puuid;

    public String getPuuid() { return puuid; }
    public void setPuuid(String puuid) { this.puuid = puuid; }
}