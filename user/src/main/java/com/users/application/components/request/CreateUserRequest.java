package com.users.application.components.request;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.users.application.components.Address;
import com.users.application.components.Role;

@JsonPropertyOrder({
        "firstName",
        "secondName",
        "email",
        "city",
        "street"
})
public class CreateUserRequest {
    public String firstName;
    public String secondName;
    public String email;
    public String city;
    public String street;
}
