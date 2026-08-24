package com.phoboy.tasktracker.controller;

import com.phoboy.tasktracker.dto.TaskResponseDto;
import com.phoboy.tasktracker.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/task")
    public ResponseEntity<List<TaskResponseDto>> getTasks(@AuthenticationPrincipal Jwt token) {

        List<TaskResponseDto> tasks = taskService.getTasks(token.getClaim("userId"));
        tasks.forEach(t -> log.info("taskDto=" + t));
        return ResponseEntity.status(HttpStatus.OK).body(tasks);

    }
}
