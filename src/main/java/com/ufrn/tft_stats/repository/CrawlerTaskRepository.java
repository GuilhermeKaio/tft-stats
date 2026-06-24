package com.ufrn.tft_stats.repository;

import com.ufrn.tft_stats.domain.CrawlerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrawlerTaskRepository extends JpaRepository<CrawlerTask, String> {

    List<CrawlerTask> findTop10ByStatus(String status);
}