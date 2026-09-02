package com.phoboy.tasktracker.summarization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GigaChatResponseDto(
        @JsonProperty("message")
        String reply
) {
}
