package com.example.notes_app.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserDto {
    private UUID id;
    private String username;
    private String email;
}
