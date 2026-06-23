package com.ufrn.tft_stats.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ufrn.tft_stats.service.InMemoryMatchQueueService;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private final InMemoryMatchQueueService matchQueueService;

    public AdminController(InMemoryMatchQueueService matchQueueService) {
        this.matchQueueService = matchQueueService;
    }

	@PostMapping("/sync")
	public String triggerSync(@RequestParam String apiKey, @RequestParam String puuid) {
		return matchQueueService.enqueueMatchesForPlayer(puuid, apiKey);
	}
}