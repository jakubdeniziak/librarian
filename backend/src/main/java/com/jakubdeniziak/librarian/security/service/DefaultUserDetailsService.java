package com.jakubdeniziak.librarian.security.service;

import com.jakubdeniziak.librarian.security.domain.UserSecurityDetails;
import com.jakubdeniziak.librarian.security.dto.RegisterRequest;
import com.jakubdeniziak.librarian.security.entity.UserSecurityEntity;
import com.jakubdeniziak.librarian.security.repository.UserSecurityRepository;
import com.jakubdeniziak.librarian.user.entity.UserEntity;
import com.jakubdeniziak.librarian.user.repository.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserSecurityRepository repository;
    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User \"%s\" not found", username)));
        return new UserSecurityDetails(user);
    }

    public boolean isUserPresent(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public void registerUser(RegisterRequest request) {
        registerAccount(request, Set.of("ROLE_USER"));
    }

    public void registerAdmin(RegisterRequest request) {
        registerAccount(request, Set.of("ROLE_ADMIN", "ROLE_USER"));
    }

    private void registerAccount(RegisterRequest request, Set<String> roles) {
        UUID userId = UUID.randomUUID();
        UserEntity userProfile = UserEntity.builder()
                .id(userId)
                .build();
        userRepository.save(userProfile);
        UserSecurityEntity user = UserSecurityEntity.builder()
                .id(userId)
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .profile(userProfile)
                .build();
        repository.save(user);
    }

}
