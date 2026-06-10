package com.ufrn.tft_stats.controller;

import com.ufrn.tft_stats.service.RiotIntegrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final RiotIntegrationService riotIntegrationService;

	public AdminController(RiotIntegrationService riotIntegrationService) {
		this.riotIntegrationService = riotIntegrationService;
	}

	@PostMapping("/sync")
	public String triggerSync(@RequestParam String apiKey, @RequestParam String puuid) {
		return riotIntegrationService.syncMatchesForPlayer(puuid, apiKey);
	}
}