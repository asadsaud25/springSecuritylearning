package com.h2.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.h2.Entity.UserEntity;

@Service
public class UserService {
    private static final String EXISTING_EMAIL = "test@test.com";
    public Optional<UserEntity> findByEmail(String email) {
        if(!EXISTING_EMAIL.equalsIgnoreCase(email)) {
            return Optional.empty();
        }

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setEmail(EXISTING_EMAIL);
        user.setPassword("$2a$12$bVD4YtW6JjQka8hzKWuA5u6qp5mQlF/uhhFtRnm4lqtWR1vInaTUC");
        user.setRole("USEr_ADMIN");
        user.setExtraInfo("My nice admin");
        return Optional.of(user);
    }
}
