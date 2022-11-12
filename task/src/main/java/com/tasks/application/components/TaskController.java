package com.tasks.application.components;

import com.tasks.application.components.request.AssignUserToTaskRequest;
import com.tasks.application.components.request.CompleteTaskRequest;
import com.tasks.application.components.request.CreateTaskRequest;
import com.tasks.application.components.request.DeleteTaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    // TODO: Implement methods for creating, updating, completing tasks
    private final TaskService taskService;

    // TODO: Test implemented methods
    @PostMapping("/createTask")
    public ResponseEntity<Void> createTask(@RequestBody CreateTaskRequest request) {
        taskService.createTask(request);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(taskService.getTasks(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/completeTask")
    public ResponseEntity<Void> completeTask(@RequestBody CompleteTaskRequest request) {
        taskService.completeTask(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/assignTo")
    public ResponseEntity<Void> assignTo(@RequestBody AssignUserToTaskRequest request) {
        taskService.assignUserToTask(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteTask(@RequestBody DeleteTaskRequest request) {
        taskService.deleteTask(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
