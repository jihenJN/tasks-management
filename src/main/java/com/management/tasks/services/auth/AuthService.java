package com.management.tasks.services.auth;

import com.management.tasks.dto.SignupRequest;
import com.management.tasks.dto.UserDto;

public interface AuthService {
    UserDto signupUser(SignupRequest signupRequest);
    boolean hasUserWithEmail(String email);
}
