package com.ufrn.tft_stats.client;

import com.ufrn.tft_stats.dto.LeagueListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "riotLeague", url = "https://br1.api.riotgames.com")
public interface RiotLeagueClient {

    @GetMapping("/tft/league/v1/challenger")
    LeagueListDto getChallengerLeague(@RequestHeader("X-Riot-Token") String apiKey);
    
}