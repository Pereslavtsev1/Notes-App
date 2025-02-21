package com.example.notes_app.repository;

import java.util.Optional;

import com.example.notes_app.model.ApplicationUser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findByEmail(String email);
}
