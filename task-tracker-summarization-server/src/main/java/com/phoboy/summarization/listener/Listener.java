package com.phoboy.summarization.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Listener {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "task.summarization.requests")
    public void listenRequest() {}
}
