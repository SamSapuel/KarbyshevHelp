package com.users.application.components;

import com.users.application.components.request.CreateUserRequest;
import com.users.application.components.request.UpdateUserRequest;
import com.users.application.components.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public List<User> fetchAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/findUser/{email}")
    public User getUserByEmail(@PathVariable String email) throws Exception {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) throws Exception {
        if (userService.getUserByEmail(request.email) != null) throw new Exception("User already exists");
        User user  = userService.createNewUser(request);
        kafkaTemplate.send("users", "user: '" + request.email + "' was created");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<User> updateUserRole(@PathVariable String email, @RequestBody UpdateUserRequest request) throws Exception {
        User user = userService.updateUserRole(email, request);
        kafkaTemplate.send("users", "user: '" + email + "' was updated");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        kafkaTemplate.send("users", "user: '" + email + "' was deleted");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
