package com.phoboy.tasktracker.service;


import com.phoboy.tasktracker.shared.dto.WelcomeMailRequestDto;
import com.phoboy.tasktracker.shared.kafka.KafkaConstants;

import com.phoboy.tasktracker.shared.dto.SummarizationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendToSummarization(SummarizationRequestDto requestDto) {
        kafkaTemplate.send(KafkaConstants.SUM_REQUESTS, requestDto);
        log.info("Summarization request sent to summarization service.");

    }

    public void sendToMail(WelcomeMailRequestDto requestDto) {
        kafkaTemplate.send(KafkaConstants.WELCOME_MAIL_REQUESTS, requestDto);
        log.info("Welcome mail sent to mailing service.");
    }
}
