package com.example.notes_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserDto {
    private String id;
    private String username;
    private String password;
    private String email;
}
