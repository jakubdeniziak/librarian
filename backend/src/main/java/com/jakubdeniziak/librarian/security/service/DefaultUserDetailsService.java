package com.jakubdeniziak.librarian.security.service;

import com.jakubdeniziak.librarian.security.domain.UserSecurityDetails;
import com.jakubdeniziak.librarian.security.entity.UserSecurityEntity;
import com.jakubdeniziak.librarian.security.repository.UserSecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserSecurityRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User \"%s\" not found", username)));
        return new UserSecurityDetails(user);
    }

    public boolean isUserPresent(String username) {
        return repository.findByUsername(username).isPresent();
    }

    public void save(UserSecurityEntity user) {
        repository.save(user);
    }

}
