package com.phoboy.tasktracker.constants;

public final class KafkaConstants {

    private KafkaConstants() {}

    public static final String REQUESTS = "task.summarization.requests";
    public static final String REQUESTS_DLT = "task.summarization.requests-dlt";
    public static final String RESULTS = "task.summarization.results";
    public static final String SUM_GROUP_ID = "summarization-worker-group";
    public static final String DLT_GROUP_ID = "summarization-dlt-group";
    public static final String HEADER_ERROR_MESSAGE = "kafka_dlt-exception-message";
    public static final String UNKNOWN_ERROR = "unknown error";

}
