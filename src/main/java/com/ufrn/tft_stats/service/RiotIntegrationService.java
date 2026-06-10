package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.client.RiotMatchClient;
import com.ufrn.tft_stats.dto.RiotMatchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiotIntegrationService {

	private final RiotMatchClient matchClient;
	private final MatchProcessorService matchProcessorService;

	public RiotIntegrationService(RiotMatchClient matchClient, MatchProcessorService matchProcessorService) {
		this.matchClient = matchClient;
		this.matchProcessorService = matchProcessorService;
	}

	public String syncMatchesForPlayer(String puuid, String apiKey) {
		try {
			System.out.println("Buscando partidas para o PUUID: " + puuid);

			List<String> matchIds = matchClient.getMatchIdsByPuuid(apiKey, puuid);
			System.out.println("Encontradas " + matchIds.size() + " partidas.");

			int processadas = 0;

			for (String matchId : matchIds) {
				System.out.println("Baixando detalhes da partida: " + matchId);
				
				RiotMatchDto matchDetails = matchClient.getMatchDetails(apiKey, matchId);
				
				matchProcessorService.processMatch(matchDetails);
				processadas++;

				Thread.sleep(1500);
			}

			return "Sucesso! " + processadas + " partidas processadas.";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro durante a sincronização: " + e.getMessage();
		}
	}
}