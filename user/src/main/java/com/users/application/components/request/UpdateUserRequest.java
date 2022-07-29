package com.users.application.components.request;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.users.application.components.Role;

@JsonPropertyOrder({"role"})
public class UpdateUserRequest {
    public Role role;
}
