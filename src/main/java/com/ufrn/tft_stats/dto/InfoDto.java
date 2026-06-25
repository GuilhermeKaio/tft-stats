package com.ufrn.tft_stats.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoDto {
	private String game_version;
	private List<ParticipantDto> participants;
	
	@JsonProperty("tft_game_type")
	private String tftGameType;

	@JsonProperty("tft_set_core_name")
	private String tftSetCoreName;

	public String getTftGameType() {
		return tftGameType;
	}

	public String getTftSetCoreName() {
		return tftSetCoreName;
	}

	public void setTftGameType(String tftGameType) {
		this.tftGameType = tftGameType;
	}

	public void setTftSetCoreName(String tftSetCoreName) {
		this.tftSetCoreName = tftSetCoreName;
	}

	public String getGame_version() {
		return game_version;
	}

	public void setGame_version(String game_version) {
		this.game_version = game_version;
	}

	public List<ParticipantDto> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantDto> participants) {
		this.participants = participants;
	}
}