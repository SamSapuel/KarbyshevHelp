package com.tasks.application.components;

import com.tasks.application.components.request.CreateTaskRequest;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
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

//    @Transactional
//    public String addTask(List<String> props) {
//        // props structure is command:label:createdBy:assignedTo
//        // TODO: Implement user validation (check if received user exists in db)
//
//        // user validation process starts below
//        // request to user microservice goes through Kafka
//
//        kafkaTemplate.send("tasks", "request:getUsers"); // just example
//
//        // after validation
//
//        Task newTask = new Task(props.get(0), props.get(1), props.get(2));
//        taskRepository.save(newTask);
//        return "create-task:OK";
//    }

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
    public Task assignUserToTask(String label, String email) {
        Task task = taskRepository.findByLabel(label);
        task.setAssignedTo(email);

        return taskRepository.save(task);
    }

//    @Transactional
//    public String deleteTask(List<String> props) {
//         // props structure is delete-task:taskName
//         // check if requested task exists in db
//         Optional<Task> checkedTask = taskRepository.findByLabel(props.get(0));
//         if (checkedTask.isEmpty()) {
//             System.err.println(String.format("task %s does not exists", props.get(0)));
//             return "delete-task:404";
//         } else {
//             taskRepository.delete(checkedTask.get());
//             return "delete-task:OK";
//         }
//     }
//
//     @Transactional
//     public String updateTask(List<String> props) {
//        // props structure is update-task:taskName:assignedTo
//        // TODO: Implement user validation
//        Optional<Task> checkedTask = taskRepository.findByLabel(props.get(0));
//        if (checkedTask.isEmpty()) {
//            System.err.println(String.format("task %s does not exists", props.get(0)));
//            return "update-task:404";
//        } else {
//            // checking if user is valid
//
//            // TODO: implement assigned user validation
//
//            // after validation
//            Task replacedTask = checkedTask.get();
//            replacedTask.setAssignedTo(props.get(1));
//            // deleting old task
//            try {
//                taskRepository.delete(checkedTask.get());
//            } catch (Exception e) {
//                throw new RuntimeException("failed to delete task" + props.get(0));
//            }
//            taskRepository.save(replacedTask);
//            return "update-task:OK";
//        }
//    }
//
//    @Transactional
//    public void completeTask(List<String> props) {
//        // TODO: perform checking if task doesn't exists or already completed
//        Optional<Task> checkedTask = taskRepository.findByLabel(props.get(0));
//        if (checkedTask.isEmpty()) {
//
//        }
//    }
}
