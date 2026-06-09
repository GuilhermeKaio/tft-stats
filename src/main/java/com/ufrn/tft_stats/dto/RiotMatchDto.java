package com.ufrn.tft_stats.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RiotMatchDto {
 private InfoDto info;

 public InfoDto getInfo() {
     return info;
 }

 public void setInfo(InfoDto info) {
     this.info = info;
 }
}
