package com.users.application.components;

import com.users.application.components.request.CreateUserRequest;
import com.users.application.components.validate.UserValidate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final CreateUserRequest createUserRequest;
    private final UserValidate userValidate;
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public User getUserByEmail(String email) { return userRepository.findUserByEmail(email); }
    @Transactional
    public User createNewUser(CreateUserRequest createUserRequest) {
        userValidate.validateOnCreate(createUserRequest.email);
        return userRepository.insert(new User(
                createUserRequest.firstName,
                createUserRequest.secondName,
                createUserRequest.email,
                createUserRequest.role,
                createUserRequest.address.city,
                createUserRequest.address.street,
                LocalDateTime.now()
        ));
    }

//    @Transactional
//    public User updateUserRole() {
//
//    }
}
