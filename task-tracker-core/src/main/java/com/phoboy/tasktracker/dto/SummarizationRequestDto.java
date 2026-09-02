package com.phoboy.tasktracker.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record SummarizationRequestDto(
        @Nonnull
        UUID id,
        @Email
        String userEmail,
        List<TaskInfo> tasks
) {

    @Override
    public String toString() {

        if (tasks.isEmpty()) {
            return "NO TASKs";
        }

        StringBuilder string = new StringBuilder();
        for (TaskInfo t : tasks) {
            string.append(String.format("[%s] %s (%s) %n", t.completedAt() == null ? TaskStatus.ACTIVE : TaskStatus.COMPLETED, t.title(), t.description() == null ? "" : t.description()));
        }

        return string.toString();
    }
}

