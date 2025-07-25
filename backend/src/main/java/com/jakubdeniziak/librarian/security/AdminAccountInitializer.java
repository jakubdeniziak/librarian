package com.jakubdeniziak.librarian.security;

import com.jakubdeniziak.librarian.security.entity.UserSecurityEntity;
import com.jakubdeniziak.librarian.security.service.DefaultUserDetailsService;
import com.jakubdeniziak.librarian.user.entity.UserEntity;
import com.jakubdeniziak.librarian.user.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AdminAccountInitializer implements ApplicationRunner {

    private static final String ADMIN_USERNAME = "ADMIN_USERNAME";
    private static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    private static final Set<String> ADMIN_ROLES = Set.of("ROLE_ADMIN", "ROLE_USER");

    private final DefaultUserDetailsService userDetailsService;
    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (System.getenv(ADMIN_USERNAME) == null || System.getenv(ADMIN_PASSWORD) == null) {
            throw new IllegalStateException("Admin credentials must be set in environment variables.");
        }
        if (userDetailsService.isUserPresent(System.getenv(ADMIN_USERNAME))) {
            return;
        }
        UUID userId = UUID.randomUUID();
        saveUser(userId);
    }

    private void saveUser(UUID userId) {
        UserEntity userProfile = UserEntity.builder()
                .id(userId)
                .build();
        userRepository.save(userProfile);
        UserSecurityEntity admin = UserSecurityEntity.builder()
                .id(userId)
                .username(System.getenv(ADMIN_USERNAME))
                .password(passwordEncoder.encode(System.getenv(ADMIN_PASSWORD)))
                .roles(ADMIN_ROLES)
                .profile(userProfile)
                .build();
        userDetailsService.save(admin);
    }

}
