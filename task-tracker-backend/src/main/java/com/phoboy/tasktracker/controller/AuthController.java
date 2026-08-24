package com.phoboy.tasktracker.controller;

import com.phoboy.tasktracker.dto.TokenResponseDto;
import com.phoboy.tasktracker.dto.UserRequestDto;
import com.phoboy.tasktracker.service.TokenService;
import com.phoboy.tasktracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> processLogin(@Valid @RequestBody UserRequestDto requestDto) {
        String token = tokenService.createToken(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(new TokenResponseDto(token));
//                .build();

    }



}
