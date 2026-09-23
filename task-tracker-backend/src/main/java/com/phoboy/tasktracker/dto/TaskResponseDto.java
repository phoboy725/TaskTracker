package com.phoboy.tasktracker.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record TaskResponseDto(
        Long id,
        String title,
        String description,
        Instant completedAt
) {
}
