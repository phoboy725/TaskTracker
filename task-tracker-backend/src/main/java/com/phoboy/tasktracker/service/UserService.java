package com.phoboy.tasktracker.service;

import com.phoboy.tasktracker.dto.UserRequestDto;
import com.phoboy.tasktracker.model.User;
import com.phoboy.tasktracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

//    @PostConstruct
//    public void init(){
//        User user = User.builder()
//                .email("zx@zx.ru")
//                .passwordHash("$2a$10$8f44uPArgkEKklFVDz8yIe34JlMmjpFPUMqrNXPlmKzcyPfOZ4I7m")
//                .build();
//
//        userRepository.save(user);
//    }


    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void saveUser(UserRequestDto requestDto) {

        User user = User.builder()
                .email(requestDto.email())
                .passwordHash(passwordEncoder.encode(requestDto.password()))
                .build();

        userRepository.save(user);
    }

}
