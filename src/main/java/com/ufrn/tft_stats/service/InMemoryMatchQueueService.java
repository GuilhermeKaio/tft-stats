package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.client.RiotMatchClient;
import com.ufrn.tft_stats.domain.ProcessedMatch;
import com.ufrn.tft_stats.dto.RiotMatchDto;
import com.ufrn.tft_stats.repository.ProcessedMatchRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class InMemoryMatchQueueService {
    private final Queue<String> filaDePartidas = new ConcurrentLinkedQueue<>();
    private final Set<String> partidasNaFila = ConcurrentHashMap.newKeySet();
    
    private final RiotMatchClient matchClient;
    private final MatchProcessorService matchProcessorService;
    private final ProcessedMatchRepository processedMatchRepository;

    public InMemoryMatchQueueService(RiotMatchClient matchClient, 
                                     MatchProcessorService matchProcessorService, 
                                     ProcessedMatchRepository processedMatchRepository) {
        this.matchClient = matchClient;
        this.matchProcessorService = matchProcessorService;
        this.processedMatchRepository = processedMatchRepository;
    }

    public String enqueueMatchesForPlayer(String puuid, String apiKey) {
        System.out.println("Buscando lista de partidas para o PUUID: " + puuid);
        List<String> matchIds = matchClient.getMatchIdsByPuuid(apiKey, puuid);
        
        int adicionadas = 0;
        for (String matchId : matchIds) {
            if (!processedMatchRepository.existsById(matchId) && !partidasNaFila.contains(matchId)) {
                filaDePartidas.offer(apiKey + "|" + matchId);
                partidasNaFila.add(matchId);
                adicionadas++;
            }
        }

        return "Sucesso! " + adicionadas + " novas partidas colocadas na fila interna para processamento. (Ignoradas por duplicação: " + (matchIds.size() - adicionadas) + ")";
    }

    @Scheduled(fixedDelay = 1500)
    public void processarProximaPartidaDaFila() {
        String mensagem = filaDePartidas.poll();

        if (mensagem != null) {
            try {
                String[] partes = mensagem.split("\\|");
                String apiKey = partes[0];
                String matchId = partes[1];

                System.out.println("[FILA INTERNA] Baixando e processando partida: " + matchId);
                System.out.println("[FILA INTERNA] Restam " + filaDePartidas.size() + " partidas na fila.");

                RiotMatchDto matchDetails = matchClient.getMatchDetails(apiKey, matchId);
                matchProcessorService.processMatch(matchDetails);
                processedMatchRepository.save(new ProcessedMatch(matchId));
                partidasNaFila.remove(matchId);
                System.out.println("[FILA INTERNA] Partida " + matchId + " salva como PROCESSADA.");

            } catch (Exception e) {
                System.err.println("[FILA INTERNA] Erro ao processar partida: " + e.getMessage());
            }
        }
    }
}