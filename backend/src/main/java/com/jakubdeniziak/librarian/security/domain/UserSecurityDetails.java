package com.jakubdeniziak.librarian.security.domain;

import com.jakubdeniziak.librarian.security.entity.UserSecurityEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserSecurityDetails implements UserDetails {

    private final UserSecurityEntity userSecurityEntity;

    @Override
    public String getUsername() {
        return userSecurityEntity.getUsername();
    }

    @Override
    public String getPassword() {
        return userSecurityEntity.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userSecurityEntity.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

}
