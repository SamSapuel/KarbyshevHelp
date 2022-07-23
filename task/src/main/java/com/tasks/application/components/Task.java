package com.tasks.application.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class Task {

    @Id
    private String id;

    private String label;
    private String createdBy;
    private String assignedTo;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public Task(String label,
                String createdBy,
                String assignedTo) {
        this.label = label;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.isCompleted = false;
        this.createdAt = LocalDateTime.now();
        this.completedAt = null;
    }
}
