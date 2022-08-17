package com.users.application.components.security.model;

import java.util.Optional;

public interface UserDetailsDao {

    Optional<UserDetails> selectApplicationUserByEmail(String email);
}
