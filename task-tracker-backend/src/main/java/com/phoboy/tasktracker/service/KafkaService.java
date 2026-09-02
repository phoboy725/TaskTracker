package com.phoboy.tasktracker.service;

import com.phoboy.tasktracker.dto.SummarizationRequestDto;
import com.phoboy.tasktracker.exception.TaskNotFoundException;
import com.phoboy.tasktracker.kafka.KafkaTopics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendToSummarization(SummarizationRequestDto requestDto)  {
        kafkaTemplate.send(KafkaTopics.REQUESTS, requestDto);
        log.info("requestDto 2 kafka=" + requestDto);
        log.info("REQUEST SENT!!!");

    }
}
