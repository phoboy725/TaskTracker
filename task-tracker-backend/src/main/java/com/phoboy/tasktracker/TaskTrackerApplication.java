package com.phoboy.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);

        // 123456 = $2a$10$8f44uPArgkEKklFVDz8yIe34JlMmjpFPUMqrNXPlmKzcyPfOZ4I7m
    }
}
