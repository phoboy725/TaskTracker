package com.phoboy.tasktracker.config;

import com.phoboy.tasktracker.constants.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic requests() {
        return new NewTopic(KafkaConstants.REQUESTS, 3, (short) 1);
    }

    @Bean
    public NewTopic results() {
        return new NewTopic(KafkaConstants.RESULTS, 3, (short) 1);
    }
}
