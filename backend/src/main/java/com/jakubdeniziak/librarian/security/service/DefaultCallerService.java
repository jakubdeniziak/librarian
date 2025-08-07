package com.jakubdeniziak.librarian.security.service;

import com.jakubdeniziak.librarian.security.domain.UserSecurityDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultCallerService implements CallerService {

    @Override
    public UUID getCallerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityDetails userDetails = (UserSecurityDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

    @Override
    public boolean isCallerAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurityDetails userDetails = (UserSecurityDetails) authentication.getPrincipal();
        return userDetails.isAdmin();
    }

}
