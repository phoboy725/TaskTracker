package com.phoboy.tasktracker.summarization.listener;

import com.phoboy.tasktracker.dto.SummarizationRequestDto;
import com.phoboy.tasktracker.kafka.KafkaTopics;
import com.phoboy.tasktracker.summarization.service.GigaChatService;
import com.phoboy.tasktracker.summarization.service.PromptBuilderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@EnableKafka
@Component
public class SummarizationListener {

    private final PromptBuilderService promptGenerationService;
    private final GigaChatService gigaChatService;

    @KafkaListener(topics = KafkaTopics.REQUESTS, groupId = "summarization-worker-group")
    public void listenRequest(SummarizationRequestDto requestDto) {

        String prompt = promptGenerationService.generatePrompt(requestDto);
        String response = gigaChatService.getAISummarization(prompt);
        
        log.info("Response=" + response);

    }
}
