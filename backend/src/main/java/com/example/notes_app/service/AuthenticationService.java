package com.example.notes_app.service;

import java.util.UUID;

import com.example.notes_app.dto.jwt.JwtResponse;
import com.example.notes_app.model.ApplicationUser;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ApplicationUserService applicationUserService;

    public UUID registerApplicationUser(ApplicationUser applicationUser) {
        return applicationUserService.createApplicationUser(applicationUser);
    }

    public JwtResponse loginApplicationUser(ApplicationUser entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginApplicationUser'");
    }

}
