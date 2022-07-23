package com.tasks.application.components;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    // TODO: Implement methods for creating, updating, completing tasks
    TaskService taskService;

    // TODO: Test implemented methods
    // TODO: Configure Apache207 Kafka :)

    @PostMapping("new-task")
    private void createTask(String[] args) {
        /**
         *
         *  Думаю, что нужно сделать что-то типа структуры, в которую
         *  будут попадать параметры для создания таска, а потом эту
         *  структуру передавать в метод
         *
         * **/
        taskService.addTask(args[0], args[1], args[2]);
    }
}
