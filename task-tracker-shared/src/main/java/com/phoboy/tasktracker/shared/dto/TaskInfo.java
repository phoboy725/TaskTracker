package com.phoboy.tasktracker.shared.dto;

public record TaskInfo(
        Long id,
        String title,
        String description,
        TaskStatus status
) {
}
