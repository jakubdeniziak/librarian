package com.jakubdeniziak.librarian.security.service;

import java.util.UUID;

public interface CallerService {

    UUID getCallerId();
    boolean isCallerAdmin();

}
