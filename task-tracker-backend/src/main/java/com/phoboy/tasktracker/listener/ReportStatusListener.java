package com.phoboy.tasktracker.listener;


import com.phoboy.tasktracker.shared.dto.ReportMailRequestDto;
import com.phoboy.tasktracker.shared.kafka.KafkaConstants;

import com.phoboy.tasktracker.shared.dto.ReportStatusDto;
import com.phoboy.tasktracker.shared.dto.SummarizationRequestDto;
import com.phoboy.tasktracker.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableKafka
public class ReportStatusListener {

    private final ReportService reportService;

    @KafkaListener(topics = KafkaConstants.SUM_RESULTS, groupId = KafkaConstants.SUM_GROUP_ID)
    public void listenReportStatus(ReportStatusDto reportStatusDto) {

        reportService.updateReportStatus(reportStatusDto.uuid(), reportStatusDto.reportStatus());

    }

    @KafkaListener(topics = KafkaConstants.SUM_REQUESTS_DLT, groupId = KafkaConstants.SUM_DLT_GROUP_ID)
    public void listenSumDlt(SummarizationRequestDto dto, @Header(name = KafkaConstants.HEADER_ERROR_MESSAGE, required = false) String errorCause) {

        reportService.addErrorAndCause(dto.uuid(),
                (errorCause == null || errorCause.isBlank())
                        ? KafkaConstants.UNKNOWN_ERROR : errorCause);
    }

    @KafkaListener(topics = KafkaConstants.MAIL_REQUESTS_DLT, groupId = KafkaConstants.MAIL_DLT_GROUP_ID)
    public void listenMailDlt(ReportMailRequestDto dto, @Header(name = KafkaConstants.HEADER_ERROR_MESSAGE, required = false) String errorCause) {
        log.info("ERROR RECEIVED" + ((errorCause == null || errorCause.isBlank()) ? KafkaConstants.UNKNOWN_ERROR : errorCause));

        reportService.addErrorAndCause(dto.uuid(),
                (errorCause == null || errorCause.isBlank())
                        ? KafkaConstants.UNKNOWN_ERROR : errorCause.substring(0, 250));
    }
}
