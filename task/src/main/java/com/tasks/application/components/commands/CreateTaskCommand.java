package com.tasks.application.components.commands;

import com.tasks.application.components.TaskController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskCommand implements CommandInterface{

    private TaskController receiver = null;
    private List<String> props = null;

    @Override
    public String execute() {
        if (receiver == null) {
            System.err.println("receiver is not assigned yet");
            throw new RuntimeException("no assigned receivers");
        }
        try {
            receiver.createTask(props);
        } catch (Exception e) {
            throw new RuntimeException("failed to execute command");
        }
        return null;
    }
}
