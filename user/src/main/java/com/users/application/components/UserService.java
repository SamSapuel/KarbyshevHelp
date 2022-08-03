package com.users.application.components;

import com.users.application.components.request.CreateUserRequest;
import com.users.application.components.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
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
    public User getUserByEmail(String email) { return userRepository.findUserByEmail(email); }
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
    public User updateUserRole(String email, UpdateUserRequest request) {
        User user = userRepository.findUserByEmail(email);
        user.setRole(request.role);
        return user;
    }

    @Transactional
    public void deleteUser(String email) throws Exception {
        if (userRepository.findUserByEmail(email) == null) {
            throw new Exception("User already deleted");
        } else {
            userRepository.delete(userRepository.findUserByEmail(email));
        }
    }

}
