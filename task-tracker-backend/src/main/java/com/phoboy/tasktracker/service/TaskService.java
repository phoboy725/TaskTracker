package com.phoboy.tasktracker.service;

import com.phoboy.tasktracker.dto.TaskResponseDto;
import com.phoboy.tasktracker.mapper.TaskMapper;
import com.phoboy.tasktracker.model.Task;
import com.phoboy.tasktracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private  final TaskMapper taskMapper;

    public List<TaskResponseDto> getTasks(Long userId) {

        List<Task> tasks = taskRepository.findTasksByUserId(userId);
        tasks.forEach(t -> log.info("task=" + t.toString()));

        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();

    }


}
