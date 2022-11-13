package com.tasks.application.components.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "label",
        "assignedTo",
        "tag"
})
public class CreateTaskRequest {
    public String label;

    public String assignedTo;

    public String tag;
}
