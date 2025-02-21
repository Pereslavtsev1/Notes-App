package com.example.notes_app.controller;

import java.util.Map;

import jakarta.validation.Valid;

import com.example.notes_app.dto.ApplicationUserDto;
import com.example.notes_app.dto.jwt.JwtRequest;
import com.example.notes_app.dto.jwt.JwtResponse;
import com.example.notes_app.mapper.ApplicationUserMapper;
import com.example.notes_app.service.AuthenticationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthenticationRestController {

    private final ApplicationUserMapper userMapper;
    private final AuthenticationService authService;

    /**
     * Registers a new user.
     *
     * @param {@link ApplicationUserDto} registrationDto User registration data.
     * @param {@link UriComponentsBuilder} uriBuilder Builder for creating the user
     *               URI.
     * @return {@link ResponseEntity} ResponseEntity with a 201 Created status and
     *         the user URI in the
     *         Location header,
     *         or a 400 Bad Request if registration fails.
     */
    @PostMapping(path = "/signup")
    public ResponseEntity<Void> signup(
            @Valid @RequestBody ApplicationUserDto registrationDto,
            UriComponentsBuilder uriBuilder) {

        try {
            var userId = authService.registerApplicationUser(userMapper.toEntity(registrationDto));
            log.info("User registered successfully with ID: {}", userId);
            return ResponseEntity.created(uriBuilder.path("/api/v1/users/{userId}")
                    .buildAndExpand(Map.of("userId", userId))
                    .toUri()).build();

        } catch (Exception e) {
            log.error("Registration failed for user: {}", registrationDto.getUsername(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Handles user login.
     *
     * @param {@link ApplicationUserDto} credentialsDto User login
     *               credentials.
     * @return {@link ResponseEntity} ResponseEntity containing the
     *         {@link JwtResponse} JWT response on
     *         successful login, or a
     *         401 Unauthorized
     *         if login fails.
     */
    @PostMapping(path = "/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody ApplicationUserDto credentialsDto) {
        try {
            JwtResponse jwtResponse = authService.loginApplicationUser(userMapper.toEntity(credentialsDto));
            log.info("User logged in successfully: {}", credentialsDto.getUsername());
            return ResponseEntity.ok(jwtResponse);

        } catch (Exception e) {
            log.warn("Login failed for user: {}", credentialsDto.getUsername(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Refreshes the JWT token. (INCOMPLETE IMPLEMENTATION)
     *
     * @param {@link JwtRequest} refreshRequest Request containing the refresh
     *               token.
     * @return {@link ResponseEntity} ResponseEntity containing the new
     *         {@link JwtResponse} JWT response,
     *         or a 401
     *         Unauthorized
     *         if refresh fails.
     */
    @PostMapping(path = "/refresh")
    public ResponseEntity<JwtResponse> refresh(@Valid @RequestBody JwtRequest refreshRequest) {

        log.warn("JWT refresh endpoint not fully implemented!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Or a more appropriate response
    }
}
