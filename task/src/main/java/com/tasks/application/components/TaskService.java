package com.tasks.application.components;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    TaskRepository taskRepository;

    KafkaTemplate<String, String> kafkaTemplate;

    void addTask(
            String label,
            String createdBy,
            String assignedTo
    ) {
        // TODO: Implement user validation (check if received user exists in db)
        taskRepository.save(new Task(label, createdBy, assignedTo));

        // user validation process starts below
        // request to user microservice goes through Kafka

        kafkaTemplate.send("tasks", "request:getUsers"); // just example
    }

    private void updateTask(Task task, String newAssignedTo) {
        // TODO: Implement user validation
        task.setAssignedTo(newAssignedTo);
    }

    private void completeTask(Task task) {
        // TODO: perform checking if task doesn't exists or already completed
        task.setCompleted(true);
    }
}
