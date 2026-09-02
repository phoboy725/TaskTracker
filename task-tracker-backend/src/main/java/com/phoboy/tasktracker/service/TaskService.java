package com.phoboy.tasktracker.service;

import com.phoboy.tasktracker.dto.TaskRequestDto;
import com.phoboy.tasktracker.dto.TaskResponseDto;
import com.phoboy.tasktracker.dto.TaskStatusRequestDto;
import com.phoboy.tasktracker.event.TaskSummarizationEvent;
import com.phoboy.tasktracker.exception.ExceptionMessage;
import com.phoboy.tasktracker.exception.TaskNotFoundException;
import com.phoboy.tasktracker.exception.UserNotFoundException;
import com.phoboy.tasktracker.mapper.TaskMapper;
import com.phoboy.tasktracker.model.Task;
import com.phoboy.tasktracker.model.TaskStatus;
import com.phoboy.tasktracker.model.User;
import com.phoboy.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final UserService userService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public List<TaskResponseDto> getTasks(Long userId) {

        List<Task> tasks = taskRepository.findTasksByUserIdOrderByIdDesc(userId);
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();

    }

    public List<TaskResponseDto> getTasksForToday(Long userId) {

        List<Task> tasks = taskRepository.findTasksByUserIdAndCreatedAtBetween(userId,
                Instant.now().minus(24, ChronoUnit.HOURS), Instant.now());

        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();

    }

    public TaskResponseDto findTaskById(Long userId, Long taskId) {
        Task task = taskRepository.findByUserIdAndId(userId, taskId).orElseThrow(() -> new TaskNotFoundException(ExceptionMessage.TASK_NOT_FOUND));
        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskResponseDto updateTask(Long userId, Long taskId, TaskStatusRequestDto requestDto) {

        Task task = taskRepository.findByUserIdAndId(userId, taskId)
                .orElseThrow(() -> new TaskNotFoundException(ExceptionMessage.TASK_NOT_FOUND));

        if (requestDto.completed()) {
            task.setTaskStatus(TaskStatus.COMPLETED);
            task.setCompletedAt(Instant.now());
        } else {
            if (!requestDto.description().equals(task.getDescription())) {
                task.setDescription(requestDto.description());
            }
            task.setTaskStatus(TaskStatus.ACTIVE);
            task.setCompletedAt(null);
        }

        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskResponseDto createTask(Long userId, TaskRequestDto taskRequestDto) {

        User user = userService.findUserByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));

        Task task = Task.builder()
                .user(user)
                .title(taskRequestDto.title())
                .description(taskRequestDto.description())
                .taskStatus(TaskStatus.ACTIVE)
                .completedAt(null)
                .build();

        taskRepository.save(task);
        kafkaTemplate.send("task.summarization.requests", new TaskSummarizationEvent(task.getUser().getId(), task.getTitle(), task.getDescription()));
        return taskMapper.toDto(task);
    }


}
