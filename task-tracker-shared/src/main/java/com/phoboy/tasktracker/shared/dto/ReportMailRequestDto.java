package com.phoboy.tasktracker.shared.dto;

import jakarta.validation.constraints.Email;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

public record ReportMailRequestDto(
        @NonNull
        UUID uuid,
        @Email
        String email,
        String message
) {
}
