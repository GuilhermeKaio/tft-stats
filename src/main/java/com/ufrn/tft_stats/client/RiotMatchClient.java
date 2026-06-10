package com.ufrn.tft_stats.client;

import com.ufrn.tft_stats.dto.RiotMatchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "riotMatch", url = "https://americas.api.riotgames.com")
public interface RiotMatchClient {

    @GetMapping("/tft/match/v1/matches/by-puuid/{puuid}/ids")
    List<String> getMatchIdsByPuuid(@RequestHeader("X-Riot-Token") String apiKey, 
                                    @PathVariable("puuid") String puuid);

    @GetMapping("/tft/match/v1/matches/{matchId}")
    RiotMatchDto getMatchDetails(@RequestHeader("X-Riot-Token") String apiKey, 
                                 @PathVariable("matchId") String matchId);
}