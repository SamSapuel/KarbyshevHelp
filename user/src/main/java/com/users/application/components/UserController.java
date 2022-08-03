package com.users.application.components;

import com.users.application.components.request.CreateUserRequest;
import com.users.application.components.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/findUser")
    public User getUserByEmail(@RequestBody String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) throws Exception {
        if (userService.getUserByEmail(request.email) != null) throw new Exception("User already exists");
        userService.createNewUser(request);
        kafkaTemplate.send("users", "user: '" + request.email + "' was created");
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<Void> updateUserRole(@PathVariable String email, @RequestBody UpdateUserRequest request) {
        userService.updateUserRole(email, request);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) throws Exception {
        userService.deleteUser(email);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
