package com.users.application.components.validate;

import com.users.application.components.User;
import com.users.application.components.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidate {

    private final UserService userService;

    public UserValidate(UserService userService) {
        this.userService = userService;
    }

    public void validateOnCreate(String email) {
        validateUserAlreadyExists(email);
    }

    public void validateOnUpdate(String email) {
        validateUserExists(email);
    }

    @SneakyThrows
    private void validateUserExists(String email) {
        if (userService.getUserByEmail(email) == null) throw new Exception("User doesnt exist");
    }

    @SneakyThrows
    public void validateUserAlreadyExists(String email) {
        if (userService.getUserByEmail(email) != null) throw new Exception("User already exists");
    }


}
