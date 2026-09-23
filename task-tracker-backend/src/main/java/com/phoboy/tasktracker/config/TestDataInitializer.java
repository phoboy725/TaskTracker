package com.phoboy.tasktracker.config;

import com.phoboy.tasktracker.model.Task;

import com.phoboy.tasktracker.repository.TaskRepository;
import com.phoboy.tasktracker.repository.UserRepository;
import com.phoboy.tasktracker.shared.dto.TaskStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
class TestDataInitializer {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @PostConstruct
    private void testTasksCreatedAtUpdateToNow() {

        List<Task> tasks = taskRepository.findTasksByUserIdOrderByIdDesc(1L);
        tasks.forEach(t -> t.setCreatedAt(Instant.now()));
        tasks.forEach(t -> { if (t.getTaskStatus() == TaskStatus.COMPLETED) {t.setCompletedAt(Instant.now());} });
        taskRepository.saveAll(tasks);

    }
}
