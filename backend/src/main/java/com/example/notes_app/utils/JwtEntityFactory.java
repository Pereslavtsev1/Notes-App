package com.example.notes_app.utils;

import com.example.notes_app.model.ApplicationUser;
import com.example.notes_app.security.JwtEntity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtEntityFactory {
    public static JwtEntity create(ApplicationUser applicationUser) {
        return JwtEntity.builder()
                .id(applicationUser.getId())
                .username(applicationUser.getEmail())
                .password(applicationUser.getPassword())
                .grantedAuthority(
                        applicationUser.getRoles().stream().map(Enum::name).map(SimpleGrantedAuthority::new).toList())
                .build();
    }
}
