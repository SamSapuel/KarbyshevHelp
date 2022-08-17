package com.users.application.components;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaMessageListener {

    @KafkaListener(topics = "users", groupId = "groupId")
    void listener(String data) {


        System.out.println("Listener received:" + data);
        /**
         * 0 index - method we want to call
         * 1 index - parameter
         * 2 index - OPTIONAL(method we need to call)
         * 3 index - source object
         */
        String[] args = data.split(":");
//        switch (args[0]) {
//            case "getUserByEmail":
//                if (userService.getUserByEmail(args[1]) != null) kafkaTemplate.send(
//                                                "tasks",
//                                                args[1] + ":" + args[2] + ":" + args[3]);
//        }
    }
}
