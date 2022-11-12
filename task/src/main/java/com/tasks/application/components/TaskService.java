package com.tasks.application.components;

import com.tasks.application.components.request.AssignUserToTaskRequest;
import com.tasks.application.components.request.CompleteTaskRequest;
import com.tasks.application.components.request.CreateTaskRequest;
import com.tasks.application.components.request.DeleteTaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public void completeTask(CompleteTaskRequest request) {
        Task task = taskRepository
                .findByLabel(request.label);
        task.setCompleted(true);
        taskRepository.save(task);
    }

    @Transactional
    public void createTask(CreateTaskRequest request) {
        Task task = new Task(request.label,
                request.createdBy);
        taskRepository.save(task);
        kafkaTemplate.send("users", "getUserByEmail:"
                + request.assignedTo
                + ":assignUserToTask"
                + ":" + request.label);
    }

    @Transactional
    public Task assignUserToTask(AssignUserToTaskRequest request) {
        Task task = taskRepository.findByLabel(request.label);
        task.setAssignedTo(request.email);

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(DeleteTaskRequest request) {
         Task task = taskRepository.findByLabel(request.label);
         taskRepository.delete(task);
     }

}
