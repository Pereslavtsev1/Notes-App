package com.example.notes_app.service;

import com.example.notes_app.dto.jwt.JwtRequest;
import com.example.notes_app.dto.jwt.JwtResponse;
import com.example.notes_app.model.ApplicationUser;
import com.example.notes_app.utils.JwtEntityFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ApplicationUserService applicationUserService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String registerApplicationUser(ApplicationUser applicationUser) {
        return applicationUserService.createApplicationUser(applicationUser);
    }

    public JwtResponse loginApplicationUser(ApplicationUser entity) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPassword()));
        ApplicationUser user = applicationUserService.findByEmail(entity.getEmail());
        return JwtResponse.builder()
                .refreshToken(jwtService.buildRefreshToken(JwtEntityFactory.create(user)))
                .accessToken(jwtService.buildAccessToken(JwtEntityFactory.create(user)))
                .build();
    }

    public JwtResponse refresh(JwtRequest refreshRequest) {
        return jwtService.refreshApplicationUserToken(refreshRequest.getToken());
    }
}
