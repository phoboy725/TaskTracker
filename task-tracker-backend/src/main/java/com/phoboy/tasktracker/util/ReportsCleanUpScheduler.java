package com.phoboy.tasktracker.util;

import com.phoboy.tasktracker.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReportsCleanUpScheduler {

    private final ReportService reportService;

    @Scheduled(cron = "${app.scheduling.cleanup-cron:0 0 * * * *    }")
    public void cleanUp() {
        int deletedReports = reportService.deleteCompletedReports();
        log.info("Deleted reports=" + deletedReports);
    }
}
