package com.phoboy.tasktracker.summarization.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record AccessTokenResponseDto(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("expires_at")
        Instant expiresAt
) {
}
