package com.tasks.application.components.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "tag"
})
public class GetTasksByTagRequest {
    public String tag;
}
