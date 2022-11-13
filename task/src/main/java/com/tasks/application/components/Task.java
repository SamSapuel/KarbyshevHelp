package com.tasks.application.components;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Getter
@Setter
public class Task {

    @Id
    private String id;

    private String label;

    private String tag;
    private String createdBy;
    private String assignedTo;
    private boolean isCompleted;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public Task(String label,
                String createdBy,
                String assignedTo,
                String tag) {
        this.label = label;
        this.tag = tag;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.isCompleted = false;
        this.createdAt = LocalDateTime.now();
        this.completedAt = null;
    }

    public Task(String label,
                String createdBy,
                String assignedTo) {
        this(label, createdBy, assignedTo, null);
    }
}
