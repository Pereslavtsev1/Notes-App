package com.example.notes_app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtEntity implements UserDetails {
    private String id;
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> grantedAuthority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

}
