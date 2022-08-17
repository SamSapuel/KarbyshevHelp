package com.users.application.components;

import com.users.application.components.request.CreateUserRequest;
import com.users.application.components.request.UpdateUserRequest;
import com.users.application.components.security.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping
    public List<User> fetchAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/findUser/{email}")
    public User getUserByEmail(@PathVariable String email) {
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

    @GetMapping(value = "currentUser")
    public Object getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @GetMapping(value = "/sendCookie")
    public void sendUser(HttpServletResponse response) throws IOException {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (o == "anonymousUser")
            throw new UserPrincipalNotFoundException("You're not logged in");

        UserDetails user = (UserDetails) o;
        response.sendRedirect("http://localhost:25585/api/v1/" + user.getUsername());
    }




}
