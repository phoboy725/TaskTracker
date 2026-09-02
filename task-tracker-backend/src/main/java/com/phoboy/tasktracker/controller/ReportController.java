package com.phoboy.tasktracker.controller;

import com.phoboy.tasktracker.service.SummarizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final SummarizationService summarizationService;

    @PostMapping("/generate")
    public ResponseEntity<Void> generateSummarizationReport(@AuthenticationPrincipal Jwt token) {
        summarizationService.createReport(token.getClaim("userId"), token.getSubject());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
