package com.phoboy.tasktracker.summarization.listener;

import com.phoboy.tasktracker.shared.dto.ReportStatus;
import com.phoboy.tasktracker.shared.dto.ReportStatusDto;
import com.phoboy.tasktracker.summarization.constants.KafkaConstants;

import com.phoboy.tasktracker.shared.dto.SummarizationRequestDto;
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

    @KafkaListener(topics = KafkaConstants.REQUESTS, groupId = KafkaConstants.SUM_GROUP_ID)
    public void listenRequest(SummarizationRequestDto requestDto) {

        String prompt = promptBuilderService.generatePrompt(requestDto);

        String response = gigaChatService.getAISummarization(prompt);

        kafkaTemplate.send(KafkaConstants.RESULTS, new ReportStatusDto(requestDto.uuid(), ReportStatus.COMPLETED));

        log.info("Response=" + response);

    }
}
