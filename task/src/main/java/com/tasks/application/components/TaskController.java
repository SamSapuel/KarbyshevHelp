package com.tasks.application.components;

import com.tasks.application.components.request.CreateTaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    // TODO: Implement methods for creating, updating, completing task


    private final TaskService taskService;


    // TODO: Test implemented methods
    // TODO: Configure Apache207 Kafka :)

//    public String createTask(List<String> props) {
//        /**
//         *
//         *  Думаю, что нужно сделать что-то типа структуры, в которую
//         *  будут попадать параметры для создания таска, а потом эту
//         *  структуру передавать в метод
//         *
//         * **/
//        try {
//            // TODO: implement properties parsing and sending response using Kafka
//            taskService.addTask(props);
//            return "task-create:Ok";
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e);
//        }
//    }
    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(HttpServletRequest httpServletRequest, @RequestBody CreateTaskRequest request) throws Exception {
        String email = RestUtils.getCookieUserId(httpServletRequest.getCookies());
        if (email.equals("0")) throw new Exception("You are not logged in");
//        RestTemplate restTemplate = new RestTemplate();
//        String uri = "http://localhost:25580/api/v1/users/findUser/" + request.assignedTo;
//        restTemplate.getForObject(uri, Object.class);
        Task task = taskService.createTask(request, email);
        Task updatedTask = taskService.assignUserToTask(task.getLabel(), request.assignedTo);
        return new ResponseEntity<>(updatedTask, HttpStatus.CREATED);
    }

}
