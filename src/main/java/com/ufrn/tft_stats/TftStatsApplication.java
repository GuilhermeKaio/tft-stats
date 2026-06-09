package com.ufrn.tft_stats;

import com.ufrn.tft_stats.domain.ChampionStats;
import com.ufrn.tft_stats.domain.TraitStats;
import com.ufrn.tft_stats.repository.ChampionStatsRepository;
import com.ufrn.tft_stats.repository.TraitStatsRepository;
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
    public CommandLineRunner carregarDadosDeTeste(
            ChampionStatsRepository championRepository,
            TraitStatsRepository traitRepository) {
        
        return args -> {
            System.out.println("A guardar dados de teste na base de dados...");
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

            championRepository.save(leona);
            championRepository.save(karma);

            TraitStats darkStar = new TraitStats("16.11", "TFT17_DarkStar", 1);
            darkStar.setTotalMatches(200L);
            darkStar.setWinCount(30L);
            darkStar.setTop4Count(100L);
            darkStar.setSumPlacement(900L);

            TraitStats mecha = new TraitStats("16.11", "TFT17_Mecha", 3);
            mecha.setTotalMatches(40L);
            mecha.setWinCount(15L); 
            mecha.setTop4Count(35L);
            mecha.setSumPlacement(90L);

            traitRepository.save(darkStar);
            traitRepository.save(mecha);
            
            System.out.println("Dados de teste guardados com sucesso!");
        };
    }
}