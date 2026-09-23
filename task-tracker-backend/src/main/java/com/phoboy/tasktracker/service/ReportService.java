package com.phoboy.tasktracker.service;

import com.phoboy.tasktracker.exception.ExceptionMessage;
import com.phoboy.tasktracker.exception.ReportNotFoundException;
import com.phoboy.tasktracker.model.Report;
import com.phoboy.tasktracker.repository.ReportRepository;
import com.phoboy.tasktracker.shared.dto.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Transactional
    public int deleteCompletedReports() {
        return reportRepository.deleteAllByStatus(ReportStatus.COMPLETED);

    }

    @Transactional
    public void updateReportStatus(UUID uuid, ReportStatus reportStatus) {

        Report report = findReportById(uuid);
        report.setStatus(reportStatus);
    }

    @Transactional
    public void addErrorAndCause(UUID uuid, String errorCause) {

        Report report = findReportById(uuid);
        report.setStatus(ReportStatus.ERROR);
        report.setErrorCause(errorCause);

    }

    private Report findReportById(UUID uuid) {
        return reportRepository.findById(uuid)
                .orElseThrow(()-> new ReportNotFoundException(ExceptionMessage.REPORT_NOT_FOUND));

    }
}
