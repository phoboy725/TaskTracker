package com.phoboy.tasktracker.event;

public record TaskSummarizationEvent(
        Long userId,
        String title,
        String description
) {
}

