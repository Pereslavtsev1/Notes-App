package com.example.notes_app.dto.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;

}
