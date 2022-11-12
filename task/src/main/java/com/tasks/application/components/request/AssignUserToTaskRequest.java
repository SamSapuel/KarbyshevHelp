package com.tasks.application.components.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "label",
        "email"
})
public class AssignUserToTaskRequest {
    public String label;
    public String email;
}
