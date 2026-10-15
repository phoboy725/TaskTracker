package com.phoboy.tasktracker.mail.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;

@Slf4j
@Configuration
public class KafkaErrorHandlerConfig {

    @Bean
    public CommonErrorHandler errorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);
        FixedBackOff fixedBackOff = new FixedBackOff(2000L, 3L);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(recoverer, fixedBackOff);
        errorHandler.addRetryableExceptions(
                ResourceAccessException.class,
                ConnectException.class
        );

        errorHandler.setRetryListeners((record, exception, deliveryAttempt) -> log.warn(
                "Ошибка отправки письма. Выполняется локальный повтор в Kafka. " +
                        "Попытка №{}. Ошибка: {}",
                deliveryAttempt,
                exception.getMessage()
        ));

        return errorHandler;
    }
}
