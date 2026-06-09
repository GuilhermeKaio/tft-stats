package com.ufrn.tft_stats.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TraitDto {
    private String name;
    private int tier_current;
    private int num_units;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getTier_current() { return tier_current; }
    public void setTier_current(int tier_current) { this.tier_current = tier_current; }

    public int getNum_units() { return num_units; }
    public void setNum_units(int num_units) { this.num_units = num_units; }
}
