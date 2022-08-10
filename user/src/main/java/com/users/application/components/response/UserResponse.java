package com.users.application.components.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.users.application.components.Address;
import com.users.application.components.Role;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "firstName",
        "secondName",
        "email",
        "role",
        "address",
        "created"
})
public class UserResponse {
    public String firstName;
    public String secondName;
    public String email;
    public Role role;
    public Address address;
    public LocalDateTime created;
}
