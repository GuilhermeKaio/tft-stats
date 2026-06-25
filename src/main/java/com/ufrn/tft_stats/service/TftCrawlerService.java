package com.ufrn.tft_stats.service;

import com.ufrn.tft_stats.client.RiotLeagueClient;
import com.ufrn.tft_stats.domain.CrawlerTask;
import com.ufrn.tft_stats.dto.LeagueItemDto;
import com.ufrn.tft_stats.dto.LeagueListDto;
import com.ufrn.tft_stats.repository.CrawlerTaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class TftCrawlerService {

	private final RiotLeagueClient leagueClient;
	private final CrawlerTaskRepository taskRepository;
	private final InMemoryMatchQueueService queueService;

	@Value("${riot.api.key}")
	private String API_KEY;

	public TftCrawlerService(RiotLeagueClient leagueClient, CrawlerTaskRepository taskRepository,
			InMemoryMatchQueueService queueService) {
		this.leagueClient = leagueClient;
		this.taskRepository = taskRepository;
		this.queueService = queueService;
	}

	// @Scheduled(initialDelay = 5000, fixedDelay = 86400000)
	@Scheduled(cron = "0 0 3 * * ?")
	public void buscarChallengersEAlimentarTabela() {
		System.out.println("[CRAWLER] Iniciando varredura noturna de Challengers...");

		try {
			LeagueListDto challengerLeague = leagueClient.getChallengerLeague(API_KEY);

			for (LeagueItemDto player : challengerLeague.getEntries()) {
				if (!taskRepository.existsById(player.getPuuid())) {
					taskRepository.save(new CrawlerTask(player.getPuuid(), "PENDENTE"));
				}
			}
			System.out.println("[CRAWLER] Tabela de tarefas atualizada com novos jogadores!");
		} catch (Exception e) {
			System.err.println("[CRAWLER] Erro ao buscar liga Challenger: " + e.getMessage());
		}
	}

	@Scheduled(fixedDelay = 60000)
	public void processarTarefasPendentes() {
		List<CrawlerTask> tarefasPendentes = taskRepository.findTop10ByStatus("PENDENTE");

		if (tarefasPendentes.isEmpty()) {
			return;
		}

		System.out.println(
				"[CRAWLER] Despachando " + tarefasPendentes.size() + " jogadores para a fila de processamento...");

		for (CrawlerTask tarefa : tarefasPendentes) {
			queueService.enqueueMatchesForPlayer(tarefa.getPuuid(), API_KEY);
			tarefa.setStatus("CONCLUIDO");
			taskRepository.save(tarefa);
		}
	}
}