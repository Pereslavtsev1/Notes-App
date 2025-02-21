package com.example.notes_app.service;

import java.util.Set;

import com.example.notes_app.exception.UserNotFoundException;
import com.example.notes_app.model.ApplicationUser;
import com.example.notes_app.model.Role;
import com.example.notes_app.repository.ApplicationUserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    public String createApplicationUser(ApplicationUser applicationUser) {
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        applicationUser.setRoles(Set.of(Role.USER));
        return applicationUserRepository
                .save(applicationUser).getId();
    }

    public ApplicationUser findByEmail(String email) {
        return applicationUserRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(
                String.format("User with provided email address: %s not found", email), HttpStatus.NOT_FOUND));
    }

    public ApplicationUser findById(String id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with provided id:  %s not found", id), HttpStatus.NOT_FOUND));
    }

}
