package com.phoboy.tasktracker.summarization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class SummarizationApplication {

    public static void main(String[] args) {SpringApplication.run(SummarizationApplication.class, args);}

}
