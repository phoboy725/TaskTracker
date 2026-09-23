package com.phoboy.tasktracker.shared.dto;


import java.util.UUID;

public record ReportStatusDto(
        UUID uuid,
        ReportStatus reportStatus
) {
}
