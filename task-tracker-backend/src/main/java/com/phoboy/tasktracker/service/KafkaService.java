package com.phoboy.tasktracker.service;


import com.phoboy.tasktracker.constants.KafkaConstants;

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
        kafkaTemplate.send(KafkaConstants.REQUESTS, requestDto);
        log.info("requestDto 2 kafka=" + requestDto);
        log.info("REQUEST SENT!!!");

    }
}
