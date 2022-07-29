package com.tasks.application.components;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TaskController {

    // TODO: Implement methods for creating, updating, completing tasks
    TaskService taskService;

    // TODO: Test implemented methods
    // TODO: Configure Apache207 Kafka :)

    public String createTask(List<String> props) {
        /**
         *
         *  Думаю, что нужно сделать что-то типа структуры, в которую
         *  будут попадать параметры для создания таска, а потом эту
         *  структуру передавать в метод
         *
         * **/
        try {
            // TODO: implement properties parsing and sending response using Kafka
            taskService.addTask(props);
            return "task-create:Ok";
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
