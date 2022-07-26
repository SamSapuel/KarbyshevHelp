package com.tasks.application.components;

import com.tasks.application.components.request.CompleteTaskRequest;
import com.tasks.application.components.request.CreateTaskRequest;
import com.tasks.application.components.request.DeleteTaskRequest;
import com.tasks.application.components.request.GetTasksByTagRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(HttpServletRequest httpServletRequest) throws Exception {
        String email = RestUtils.getCookieUserId(httpServletRequest.getCookies());
        if (email.equals("0")) throw new Exception("You are not logged in");
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity<List<Task>> getTasksByTag(HttpServletRequest httpServletRequest,
                                                    @RequestBody GetTasksByTagRequest request) throws Exception {
        String email = RestUtils.getCookieUserId(httpServletRequest.getCookies());
        if (email.equals("0")) throw new Exception("You are not logged in");
        List<Task> tasks = taskService.getAllTasksByTag(request.tag);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(tasks, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(HttpServletRequest httpServletRequest,
                                           @RequestBody CreateTaskRequest request) throws Exception {
        String email = RestUtils.getCookieUserId(httpServletRequest.getCookies());
        if (email.equals("0")) throw new Exception("You are not logged in");
//        RestTemplate restTemplate = new RestTemplate();
//        String uri = "http://localhost:25580/api/v1/users/findUser/" + request.assignedTo;
//        restTemplate.getForObject(uri, Object.class);
        Task task = taskService.createTask(request, email);
        Task updatedTask = taskService.assignUserToTask(task.getLabel(), request.assignedTo);
        kafkaTemplate.send("tasks", "task: '" + request.label + "' was created");
        return new ResponseEntity<>(updatedTask, HttpStatus.CREATED);
    }

    @PostMapping("/completeTask")
    public ResponseEntity<Void> completeTask(HttpServletRequest httpServletRequest,
                                             @RequestBody CompleteTaskRequest request) throws Exception {
        String email = RestUtils.getCookieUserId(httpServletRequest.getCookies());
        if (email.equals("0")) throw new Exception("You are not logged in");
        taskService.completeTask(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/deleteTask")
    public ResponseEntity<Void> deleteTask(HttpServletRequest httpServletRequest,
                                           @RequestBody DeleteTaskRequest request) throws Exception {
        String email = RestUtils.getCookieUserId(httpServletRequest.getCookies());
        if (email.equals("0")) throw new Exception("You are not logged in");
        taskService.deleteTask(request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
