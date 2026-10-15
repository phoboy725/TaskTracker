package com.phoboy.tasktracker.mail.listener;

import com.phoboy.tasktracker.shared.dto.ReportMailRequestDto;
import com.phoboy.tasktracker.shared.dto.ReportStatus;
import com.phoboy.tasktracker.shared.dto.ReportStatusDto;
import com.phoboy.tasktracker.shared.dto.WelcomeMailRequestDto;
import com.phoboy.tasktracker.shared.kafka.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@EnableKafka
@RequiredArgsConstructor
@Component
public class MailListener {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String setFrom;

    @KafkaListener(topics = KafkaConstants.SUM_MAIL_REQUESTS, groupId = KafkaConstants.MAIL_GROUP_ID)
    public void listenSummarization(ReportMailRequestDto requestDto) {

        mailSender.send(generateReportMailMessage(requestDto));
        kafkaTemplate.send(KafkaConstants.SUM_RESULTS, new ReportStatusDto(requestDto.uuid(), ReportStatus.COMPLETED));
        log.info("Summarization email sent");
    }

    @KafkaListener(topics = KafkaConstants.WELCOME_MAIL_REQUESTS, groupId = KafkaConstants.MAIL_GROUP_ID)

    public void listenWelcomeMail(WelcomeMailRequestDto requestDto) {
        mailSender.send(generateWelcomeMailMessage(requestDto));
        log.info("Welcome email sent");
    }

    private SimpleMailMessage generateReportMailMessage(ReportMailRequestDto requestDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo("phoboyhere@gmail.com"); // ЭТО ДЛЯ ТЕСТОВ!!! ЗАМЕНИТЬ НА Мэил из ДТО
        message.setSubject("Ваш отчет по задачам");
        message.setText(requestDto.message());
        return message;
    }

    private SimpleMailMessage generateWelcomeMailMessage(WelcomeMailRequestDto requestDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(requestDto.email());
        message.setSubject("Добро пожаловать в наш сервис!!!");
        message.setText("Это таск трэкер!!!");
        return message;
    }
}
