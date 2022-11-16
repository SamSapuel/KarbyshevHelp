package com.tasks.application.components.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "label"
})
public class CompleteTaskRequest {
    public String label;
}
