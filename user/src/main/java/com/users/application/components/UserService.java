package com.users.application.components;

import com.users.application.components.exception.NotFoundException;
import com.users.application.components.request.CreateUserRequest;
import com.users.application.components.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public User getUserByEmail(String email) throws Exception {
        return userRepository.findUserByEmail(email).orElseThrow(Exception::new);
    }
    @Transactional
    public User createNewUser(CreateUserRequest createUserRequest) {
        return userRepository.insert(new User(
                createUserRequest.firstName,
                createUserRequest.secondName,
                createUserRequest.email,
                Role.DEFAULT,
                new Address(createUserRequest.city,
                        createUserRequest.street),
                LocalDateTime.now()
        ));
    }

    @Transactional
    public User updateUserRole(String email, UpdateUserRequest request) throws Exception {
        User user = userRepository.findUserByEmail(email).orElseThrow(Exception::new);
        user.setRole(request.role);
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String email) {
        if (userRepository.findUserByEmail(email).isEmpty()) {
            throw NotFoundException.create("user", email);
        } else {
            userRepository.delete(userRepository.findUserByEmail(email).get());
        }
    }

}
