package com.phoboy.tasktracker.dto;

public record TaskStatusRequestDto(
        String title,
        String description,
        boolean completed
) {
}
