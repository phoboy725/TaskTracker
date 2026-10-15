package com.phoboy.tasktracker.scheduler.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class SchedulerService {

    @Scheduled(cron = "0 0 0 * * *")
    public void requestDailySummarization() {

    }

}
