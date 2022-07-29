package com.users.application.components;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) { return userRepository.findUserByEmail(email); }

    public User createNewUser() {

    }


}
