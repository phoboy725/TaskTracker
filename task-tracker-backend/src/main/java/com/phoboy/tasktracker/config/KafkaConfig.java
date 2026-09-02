package com.phoboy.tasktracker.config;

import com.phoboy.tasktracker.kafka.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic requests() {
        return new NewTopic(KafkaTopics.REQUESTS, 3, (short) 1);
    }

    @Bean
    public NewTopic results() {
        return new NewTopic(KafkaTopics.RESULTS, 3, (short) 1);
    }
}
