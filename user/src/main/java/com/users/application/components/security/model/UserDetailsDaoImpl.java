package com.users.application.components.security.model;

import com.users.application.components.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDetailsDaoImpl implements UserDetailsDao{
    private final UserRepository userRepository;

    @Override
    public Optional<UserDetails> selectApplicationUserByEmail(String email) {
        return Optional.of(
                new UserDetails(userRepository.findUserByEmail(email).orElseThrow())
        );
    }
}
