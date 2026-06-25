package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.client.RiotMatchClient;
import com.ufrn.tft_stats.domain.ProcessedMatch;
import com.ufrn.tft_stats.dto.RiotMatchDto;
import com.ufrn.tft_stats.repository.ProcessedMatchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InMemoryMatchQueueService {

    private final RiotMatchClient matchClient;
    private final MatchProcessorService matchProcessorService;
    private final ProcessedMatchRepository processedMatchRepository;

    @Value("${riot.api.key}")
    private String apiKey;

    public InMemoryMatchQueueService(RiotMatchClient matchClient, 
                                     MatchProcessorService matchProcessorService, 
                                     ProcessedMatchRepository processedMatchRepository) {
        this.matchClient = matchClient;
        this.matchProcessorService = matchProcessorService;
        this.processedMatchRepository = processedMatchRepository;
    }

    public String enqueueMatchesForPlayer(String puuid, String keyParam) {
        List<String> matchIds = matchClient.getMatchIdsByPuuid(keyParam, puuid);
        
        int adicionadas = 0;
        for (String matchId : matchIds) {
            if (!processedMatchRepository.existsById(matchId)) {
                processedMatchRepository.save(new ProcessedMatch(matchId, "PENDENTE"));
                adicionadas++;
            }
        }

        return "Sucesso! " + adicionadas + " partidas guardadas na BASE DE DADOS para processamento seguro.";
    }

    @Scheduled(fixedDelay = 1500)
    public void processNextMatch() {
        Optional<ProcessedMatch> partidaPendente = processedMatchRepository.findFirstByStatus("PENDENTE");

        if (partidaPendente.isPresent()) {
            ProcessedMatch partida = partidaPendente.get();
            String matchId = partida.getMatchId();

            try {
                System.out.println("[FILA SEGURA] Processando partida: " + matchId);
                RiotMatchDto matchDetails = matchClient.getMatchDetails(apiKey, matchId);
                
                String gameType = matchDetails.getInfo().getTftGameType();
                String setCoreName = matchDetails.getInfo().getTftSetCoreName();
                if ("standard".equals(gameType) && "TFTSet17".equals(setCoreName)) {
                    matchProcessorService.processMatch(matchDetails);
                    System.out.println("[FILA SEGURA] Partida " + matchId + " processada com sucesso!");
                } else {
                    System.out.println("[FILA SEGURA] Partida " + matchId + " IGNORADA. (Motivo - Tipo: " + gameType + " / Set: " + setCoreName + ")");
                }

                partida.setStatus("CONCLUIDO");
                processedMatchRepository.save(partida);

            } catch (Exception e) {
                System.err.println("[FILA SEGURA] Erro ao processar partida " + matchId + ": " + e.getMessage());
                
                partida.setStatus("ERRO");
                processedMatchRepository.save(partida);
            }
        }
    }
}