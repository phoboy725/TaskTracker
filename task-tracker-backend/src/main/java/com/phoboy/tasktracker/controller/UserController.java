package com.phoboy.tasktracker.controller;

import com.phoboy.tasktracker.dto.TokenResponseDto;
import com.phoboy.tasktracker.dto.UserRequestDto;
import com.phoboy.tasktracker.dto.UserResponseDto;
import com.phoboy.tasktracker.service.KafkaService;
import com.phoboy.tasktracker.service.TokenService;
import com.phoboy.tasktracker.service.UserService;
import com.phoboy.tasktracker.shared.dto.WelcomeMailRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final TokenService tokenService;
    private final UserService userService;
    private final KafkaService kafkaService;


    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUser(@AuthenticationPrincipal Jwt token) {
        UserResponseDto responseDto = tokenService.getUserCredentialsFromToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/user")
    public ResponseEntity<TokenResponseDto> processRegister(@Valid @RequestBody UserRequestDto requestDto) {

        userService.saveUser(requestDto);
        String token = tokenService.createToken(requestDto);
        kafkaService.sendToMail(new WelcomeMailRequestDto(requestDto.email()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TokenResponseDto(token));

    }

}
