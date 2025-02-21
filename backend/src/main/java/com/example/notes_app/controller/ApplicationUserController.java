package com.example.notes_app.controller;

import com.example.notes_app.dto.ApplicationUserDto;
import com.example.notes_app.mapper.ApplicationUserMapper;
import com.example.notes_app.service.ApplicationUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;
    private final ApplicationUserMapper applicationUserMapper;

    @GetMapping(path = "/{user-id}")
    public ResponseEntity<ApplicationUserDto> handleGetApplicationUser(@PathVariable(name = "user-id") String userId) {
        return ResponseEntity.ok(applicationUserMapper.toDto(applicationUserService.findById(userId)));
    }
}
