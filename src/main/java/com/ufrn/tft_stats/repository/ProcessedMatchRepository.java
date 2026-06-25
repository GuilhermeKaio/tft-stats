package com.ufrn.tft_stats.repository;

import com.ufrn.tft_stats.domain.ProcessedMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessedMatchRepository extends JpaRepository<ProcessedMatch, String> {
    
    Optional<ProcessedMatch> findFirstByStatus(String status);
}