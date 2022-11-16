package com.tasks.application.components;

import com.tasks.application.components.request.CompleteTaskRequest;
import com.tasks.application.components.request.CreateTaskRequest;
import com.tasks.application.components.request.DeleteTaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public List<Task> getAllTasksByTag(String tag) {
        Optional<List<Task>> data = taskRepository.findAllByTag(tag);
        if (data.isEmpty()) {
            return new ArrayList<>();
        }
        return data.get();
    }

    @Transactional
    public Task createTask(CreateTaskRequest request, String createdBy) {
        Task task = new Task(request.label, createdBy, request.assignedTo, request.tag);
        return taskRepository.save(task);
    }

    @Transactional
    public Task assignUserToTask(String label, String email) {
        Task task = taskRepository.findByLabel(label);
        task.setAssignedTo(email);
        return taskRepository.save(task);
    }

    @Transactional
    public void completeTask(CompleteTaskRequest request) {
        Task task = taskRepository.findByLabel(request.label);
        task.setCompleted(true);
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(DeleteTaskRequest request) {
        Task task = taskRepository.findByLabel(request.label);
        taskRepository.delete(task);
    }
}
