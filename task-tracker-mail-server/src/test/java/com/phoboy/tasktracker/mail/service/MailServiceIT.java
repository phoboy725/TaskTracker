package com.phoboy.tasktracker.mail.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class MailServiceIT {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Test
    void mailSendTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("soil7878@yandex.ru");
        message.setTo("soil78@mail.ru");
        message.setSubject("Важная системная сводка по задачам таск-трекера");
        message.setText("Здравствуйте! Это автоматическое системное уведомление. " +
                "Ваш отчет по выполненным задачам за день успешно сгенерирован ИИ-ассистентом. " +
                "Пожалуйста, ознакомьтесь с деталями в личном кабинете приложения.");
        mailSender.send(message);
    }


}
