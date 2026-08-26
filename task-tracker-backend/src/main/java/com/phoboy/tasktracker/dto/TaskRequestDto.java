package com.phoboy.tasktracker.dto;

import jakarta.annotation.Nonnull;

public record TaskRequestDto(
        @Nonnull
        String title,
        String description
) {
}
