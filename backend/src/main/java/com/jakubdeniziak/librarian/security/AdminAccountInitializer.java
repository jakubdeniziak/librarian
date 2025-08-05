package com.jakubdeniziak.librarian.security;

import com.jakubdeniziak.librarian.security.dto.RegisterRequest;
import com.jakubdeniziak.librarian.security.service.DefaultUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminAccountInitializer implements ApplicationRunner {

    private static final String ADMIN_USERNAME_ENV = "ADMIN_USERNAME";
    private static final String ADMIN_PASSWORD_ENV = "ADMIN_PASSWORD";

    private final DefaultUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (System.getenv(ADMIN_USERNAME_ENV) == null || System.getenv(ADMIN_PASSWORD_ENV) == null) {
            throw new IllegalStateException("Admin credentials must be set in environment variables.");
        }
        if (userDetailsService.isUserPresent(System.getenv(ADMIN_USERNAME_ENV))) {
            return;
        }
        RegisterRequest request = RegisterRequest.builder()
                .username(System.getenv(ADMIN_USERNAME_ENV))
                .password(passwordEncoder.encode(System.getenv(ADMIN_PASSWORD_ENV)))
                .build();
        userDetailsService.registerAdmin(request);
    }

}
