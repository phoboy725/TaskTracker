package com.phoboy.tasktracker.summarization.dto;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SummarizationResponsetDto(
        @Nonnull
        UUID id,
        SummarizationStatus status,
        String reply
) {
}
