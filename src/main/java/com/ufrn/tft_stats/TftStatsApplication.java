package com.ufrn.tft_stats;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TftStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TftStatsApplication.class, args);
    }

    @Bean
    public CommandLineRunner carregarDadosDeTeste(ChampionStatsRepository repository) {
        return args -> {
            System.out.println("Salvando dados de teste no banco...");
            
            ChampionStats leona = new ChampionStats("16.11", "TFT17_Leona", 2);
            leona.setTotalMatches(100L);
            leona.setWinCount(20L);
            leona.setTop4Count(60L);
            leona.setSumPlacement(350L);

            ChampionStats karma = new ChampionStats("16.11", "TFT17_Karma", 1);
            karma.setTotalMatches(50L);
            karma.setWinCount(2L);
            karma.setTop4Count(10L);
            karma.setSumPlacement(300L);

            repository.save(leona);
            repository.save(karma);
            
            System.out.println("Dados de teste salvos com sucesso!");
        };
    }
}
