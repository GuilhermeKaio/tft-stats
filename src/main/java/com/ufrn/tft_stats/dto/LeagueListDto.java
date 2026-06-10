package com.ufrn.tft_stats.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueListDto {
    private List<LeagueItemDto> entries;

    public List<LeagueItemDto> getEntries() { return entries; }
    public void setEntries(List<LeagueItemDto> entries) { this.entries = entries; }
}