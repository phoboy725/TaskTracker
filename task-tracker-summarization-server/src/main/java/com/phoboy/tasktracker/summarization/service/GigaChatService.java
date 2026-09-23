package com.phoboy.tasktracker.summarization.service;

import com.phoboy.tasktracker.summarization.constants.PromptConstants;
import com.phoboy.tasktracker.summarization.dto.AccessTokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class GigaChatService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    @Value("${authorization.key}")
    private String authorizationKey;

    @Cacheable(value = "gigachat-token", key = "'default'")
    public String getAccessToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("scope", "GIGACHAT_API_PERS");
        String rqUid = UUID.randomUUID().toString();

        AccessTokenResponseDto response = restClient.post()
                .uri("https://ngw.devices.sberbank.ru:9443/api/v2/oauth")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + authorizationKey)
                .header("RqUID", rqUid)
                .body(body)
                .retrieve()
                .body(AccessTokenResponseDto.class);
        
        return response != null ? response.accessToken() : null;
    }

    public String getAISummarization(String prompt) {

        Map<String, Object> requestBody = Map.of(
                "model", "GigaChat-2",
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content", PromptConstants.GIGACHAT_SYSTEM_PROMPT
                        ),
                        Map.of(
                                "role", "user",
                                "content", PromptConstants.PROMPT_HEADER + prompt
                        )
                ),
                "temperature", 0.3
        );


        String rawJson = restClient.post()
                .uri("https://api.giga.chat/v1/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + getAccessToken())
                .body(requestBody)
                .retrieve()
                .body(String.class);

        try {

            JsonNode root = objectMapper.readTree(rawJson);

            return root.path("choices")
                    .get(0)
                    .path("message")
                    .path("content")
                    .asText();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось распарсить ответ от Сбера", e);
        }
    }
}
