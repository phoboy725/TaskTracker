package com.phoboy.tasktracker.summarization.listener;

import com.phoboy.tasktracker.shared.dto.ReportMailRequestDto;
import com.phoboy.tasktracker.shared.dto.ReportStatus;
import com.phoboy.tasktracker.shared.dto.ReportStatusDto;


import com.phoboy.tasktracker.shared.dto.SummarizationRequestDto;
import com.phoboy.tasktracker.shared.kafka.KafkaConstants;
import com.phoboy.tasktracker.summarization.service.GigaChatService;
import com.phoboy.tasktracker.summarization.service.PromptBuilderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@EnableKafka
@Component
public class SummarizationListener {

    private final PromptBuilderService promptBuilderService;
    private final GigaChatService gigaChatService;
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @KafkaListener(topics = KafkaConstants.SUM_REQUESTS, groupId = KafkaConstants.SUM_GROUP_ID)
    public void listenRequest(SummarizationRequestDto requestDto) {

        String prompt = promptBuilderService.generatePrompt(requestDto);

        String aiSummarization = gigaChatService.getAISummarization(prompt);

        kafkaTemplate.send(KafkaConstants.SUM_RESULTS, new ReportStatusDto(requestDto.uuid(), ReportStatus.SENDING_MAIL));

        ReportMailRequestDto reportMailRequestDto = new ReportMailRequestDto(requestDto.uuid(), requestDto.userEmail(), aiSummarization);

        kafkaTemplate.send(KafkaConstants.SUM_MAIL_REQUESTS, reportMailRequestDto);
        log.info("Mail request sent to mail service.");

    }
}
