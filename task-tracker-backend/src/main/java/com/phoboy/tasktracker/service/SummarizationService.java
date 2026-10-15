package com.phoboy.tasktracker.service;


import com.phoboy.tasktracker.dto.TaskResponseDto;
import com.phoboy.tasktracker.exception.ExceptionMessage;
import com.phoboy.tasktracker.exception.NoTasksForReport;
import com.phoboy.tasktracker.exception.UserNotFoundException;
import com.phoboy.tasktracker.model.Report;
import com.phoboy.tasktracker.shared.dto.ReportStatus;
import com.phoboy.tasktracker.shared.dto.SummarizationRequestDto;
import com.phoboy.tasktracker.shared.dto.TaskInfo;
import com.phoboy.tasktracker.shared.dto.TaskStatus;
import com.phoboy.tasktracker.model.User;
import com.phoboy.tasktracker.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SummarizationService {

    private final TaskService taskService;
    private final UserService userService;
    private final ReportRepository reportRepository;
    private final KafkaService kafkaService;

    public void createReport(Long userId, String userEmail) {

        List<TaskResponseDto> tasks = taskService.getTasksForToday(userId);

        if (tasks.isEmpty()) { throw new NoTasksForReport(ExceptionMessage.NO_TASKS_FOR_REPORT); }

        UUID reportId = UUID.randomUUID();
        User user = userService.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));

        Report report = new Report(reportId, user, ReportStatus.GENERATING_REPORT, null ,Instant.now());

        reportRepository.saveAndFlush(report);

        List<TaskInfo> tasksInfo = tasks.stream()
                .map(t -> new TaskInfo(t.id(),
                        t.title(),
                        t.description(),
                        t.completedAt() == null ? TaskStatus.ACTIVE : TaskStatus.COMPLETED)).toList();

        SummarizationRequestDto requestDto = new SummarizationRequestDto(reportId, userEmail, tasksInfo);

        kafkaService.sendToSummarization(requestDto);
    }
}
