package com.tasks.application.components;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    // Default listener method

    @KafkaListener(
            topics = "tasks",
            groupId = "groupId"
    )
    String listener(String data) {
        return data;
    }
}
