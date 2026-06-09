package com.ufrn.tft_stats.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantDto {
    private int placement;
    private List<UnitDto> units;
    private List<TraitDto> traits;

    public int getPlacement() { return placement; }
    public void setPlacement(int placement) { this.placement = placement; }

    public List<UnitDto> getUnits() { return units; }
    public void setUnits(List<UnitDto> units) { this.units = units; }

    public List<TraitDto> getTraits() { return traits; }
    public void setTraits(List<TraitDto> traits) { this.traits = traits; }
}
