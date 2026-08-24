package com.phoboy.tasktracker.service;

import com.phoboy.tasktracker.dto.UserRequestDto;
import com.phoboy.tasktracker.dto.UserResponseDto;
import com.phoboy.tasktracker.exception.ExceptionMessage;
import com.phoboy.tasktracker.exception.UserNotFoundException;
import com.phoboy.tasktracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final UserService userService;

    public String createToken(UserRequestDto requestDto) {

        Instant now = Instant.now();

        User user = userService.findUserByEmail(requestDto.email())
                .orElseThrow(()-> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("task-tracker-backend")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("userId", user.getId())
                .subject(user.getEmail())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(jwsHeader, claims);

        return jwtEncoder.encode(parameters).getTokenValue();

    }

    public UserResponseDto getUserCredentialsFromToken(Jwt token) {
        return new UserResponseDto(token.getClaim("userId"), token.getSubject());
    }


}
