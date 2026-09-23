package com.phoboy.tasktracker.summarization.constants;

public final class KafkaConstants {

    private KafkaConstants() {}

    public static final String REQUESTS = "task.summarization.requests";
    public static final String REQUESTS_DLT = "task.summarization.requests-dlt";
    public static final String RESULTS = "task.summarization.results";
    public static final String SUM_GROUP_ID = "summarization-worker-group";
}
