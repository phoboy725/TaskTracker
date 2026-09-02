package com.phoboy.tasktracker.controller;

import com.phoboy.tasktracker.dto.TaskRequestDto;
import com.phoboy.tasktracker.dto.TaskResponseDto;
import com.phoboy.tasktracker.dto.TaskStatusRequestDto;
import com.phoboy.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getTasksOfUser(@AuthenticationPrincipal Jwt token) {

        List<TaskResponseDto> tasks = taskService.getTasks(token.getClaim("userId"));
        return ResponseEntity.status(HttpStatus.OK).body(tasks);

    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto requestDto, @AuthenticationPrincipal Jwt token) {

        TaskResponseDto responseDto = taskService.createTask(token.getClaim("userId"), requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@AuthenticationPrincipal Jwt token,
                                                      @PathVariable("id") Long taskId,
                                                      @RequestBody TaskStatusRequestDto requestDto) {

        TaskResponseDto responseDto = taskService.updateTask(token.getClaim("userId"), taskId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
