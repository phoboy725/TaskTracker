package com.phoboy.tasktracker.shared.kafka;

public final class KafkaConstants {

    private KafkaConstants() {}

    public static final String SUM_REQUESTS = "summarization.task.requests";
    public static final String SUM_REQUESTS_DLT = "summarization.task.requests.DLT";
    public static final String SUM_RESULTS = "summarization.mail.results";
    public static final String SUM_MAIL_REQUESTS = "summarization.mail.requests";
    public static final String WELCOME_MAIL_REQUESTS = "welcome.mail.requests";
    public static final String MAIL_REQUESTS_DLT = "summarization.mail.requests.DLT";
    public static final String SCH_REQUESTS_DLT = "scheduler.requests.DLT";

    public static final String SUM_GROUP_ID = "summarization-worker-group";
    public static final String SUM_DLT_GROUP_ID = "summarization-dlt-group";
    public static final String MAIL_GROUP_ID = "mail-worker-group";
    public static final String SCH_GROUP_ID = "scheduler-worker-group";
    public static final String SCH_DLT_GROUP_ID = "scheduler-dlt-group";
    public static final String MAIL_DLT_GROUP_ID = "mail-dlt-group";
    public static final String HEADER_ERROR_MESSAGE = "kafka_dlt-exception-message";
    public static final String UNKNOWN_ERROR = "unknown error";
}
