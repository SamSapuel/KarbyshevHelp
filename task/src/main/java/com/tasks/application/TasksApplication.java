package com.tasks.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }
}
