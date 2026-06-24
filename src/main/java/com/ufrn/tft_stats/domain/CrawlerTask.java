package com.ufrn.tft_stats.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CrawlerTask {

    @Id
    private String puuid;
    
    private String status;

    public CrawlerTask() {}

    public CrawlerTask(String puuid, String status) {
        this.puuid = puuid;
        this.status = status;
    }

    public String getPuuid() { return puuid; }
    public void setPuuid(String puuid) { this.puuid = puuid; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}