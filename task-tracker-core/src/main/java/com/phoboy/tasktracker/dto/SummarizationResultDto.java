package com.phoboy.tasktracker.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SummarizationResultDto(
        @Nonnull
        UUID id,
        SummarizationStatus status,
        String reply
) {
}
