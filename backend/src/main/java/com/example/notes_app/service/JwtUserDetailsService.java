package com.example.notes_app.service;

import com.example.notes_app.utils.JwtEntityFactory;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final ApplicationUserService applicationUserService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return JwtEntityFactory.create(applicationUserService.findByEmail(email));
    }

}
