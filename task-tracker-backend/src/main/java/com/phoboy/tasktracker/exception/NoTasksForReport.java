package com.phoboy.tasktracker.exception;

public class NoTasksForReport extends RuntimeException {
    public NoTasksForReport(String message) {
        super(message);
    }

    public NoTasksForReport(String message, Throwable cause) {
        super(message, cause);
    }
}
