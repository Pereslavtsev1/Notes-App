package com.example.notes_app.service;

import java.util.UUID;

import com.example.notes_app.model.ApplicationUser;
import com.example.notes_app.repository.ApplicationUserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UUID createApplicationUser(ApplicationUser applicationUser) {
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        return applicationUserRepository
                .save(applicationUser).getId();
    }

}
