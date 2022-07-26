package com.users.application.components;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class User {
    @Id
    private String id;
    private String firstName;
    private String secondName;
    private String email;
    private Role role;
    private Address address;
    private LocalDateTime created;
    private String password;

    public User(String firstName,
                String secondName,
                String email,
                String password,
                Role role,
                Address address,
                LocalDateTime created) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.address = address;
        this.created = created;
    }
}

