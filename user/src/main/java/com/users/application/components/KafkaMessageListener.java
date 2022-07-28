package com.users.application.components;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "users", groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener received:" + data);
    }
}
