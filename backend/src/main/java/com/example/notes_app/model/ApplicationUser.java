package com.example.notes_app.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collation = "application-users")
@Data
public class ApplicationUser {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;
}
