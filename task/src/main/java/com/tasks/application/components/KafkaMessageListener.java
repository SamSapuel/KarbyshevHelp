package com.tasks.application.components;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class KafkaMessageListener { // Should implement Runnable interface (or shouldn't?)

    // Default listener method

    final String separator = ":";
    private final TaskController taskController;
    @Autowired
    private final TaskService taskService;
    List<String> args = null;

    /**
     * Listener accepts command from message broker
     * and parses it into a List and calls run() method
     * so command could be handled in separate thread
     * **/
//    @KafkaListener(
//            topics = "tasks",
//            groupId = "groupId"
//    )
//    String listener(String data) {
//        System.out.println("received data: " + data);
//        args = Arrays.asList(data.split(separator));
//
//        // calling command executers down below?
//
//        return data;
//    }
    @KafkaListener(topics = "tasks", groupId = "groupId")
    void listener(String data) {
        System.out.println("received data: " + data);

        // !!!  taskService.assignUserToTask();

        String[] args = data.split(":");

        switch (args[1]) {
            case "assignUserToTask":
                taskService.assignUserToTask(args[2], args[0]);
        }
    }

    /**
     * The code below runs in a separated thread
     * basically handling commands in concurrent thread
     * **/

    // TODO: limit number of possible threads by 2 or 3

    /**
    @Override
    public void run() {
        String command = args.get(0);

        List<String> props = args.stream()
                .skip(0)
                .toList();

        switch (command) {
            case "new-task":
                taskController.createTask(props);

                break;
        }
    }
    **/
}
