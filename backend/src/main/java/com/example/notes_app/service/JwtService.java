package com.example.notes_app.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.example.notes_app.dto.jwt.JwtResponse;
import com.example.notes_app.exception.InvalidTokenException;
import com.example.notes_app.model.ApplicationUser;
import com.example.notes_app.security.JwtEntity;
import com.example.notes_app.utils.JwtEntityFactory;
import com.example.notes_app.utils.JwtProperties;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final ApplicationUserService applicationUserService;

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String buildAccessToken(JwtEntity jwtEntity) {
        return Jwts.builder().subject(jwtEntity.getUsername())
                .id(jwtEntity.getId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (jwtProperties.getAccess() * 60 * 1000)))
                .claims(Map.of("roles", jwtEntity.getGrantedAuthority()))
                .signWith(getSignInKey())
                .compact();
    }

    public String buildRefreshToken(JwtEntity jwtEntity) {
        return Jwts.builder().subject(jwtEntity.getUsername())
                .id(jwtEntity.getId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (jwtProperties.getRefresh() * 60 * 1000)))
                .signWith(getSignInKey())
                .compact();
    }

    public JwtResponse refreshApplicationUserToken(String refreshToken) {
        if (!isTokenValid(refreshToken)) {
            throw new InvalidTokenException(String.format("Provided token is invalid token: %s", refreshToken),
                    HttpStatus.FORBIDDEN);
        }
        ApplicationUser applicationUser = applicationUserService.findByEmail(extractUsername(refreshToken));
        return JwtResponse.builder().accessToken(buildAccessToken(JwtEntityFactory.create(applicationUser)))
                .refreshToken(
                        buildRefreshToken(JwtEntityFactory.create(applicationUser)))
                .build();
    }

    public boolean isTokenValid(String refreshToken) {
        return isTokenExpired(refreshToken);
    }

    private boolean isTokenExpired(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public Authentication getAuthentication(String token) {
        JwtEntity jwtEntity = (JwtEntity) jwtUserDetailsService.loadUserByUsername(extractUsername(token));
        return new UsernamePasswordAuthenticationToken(jwtEntity, "", jwtEntity.getGrantedAuthority());
    }

}
