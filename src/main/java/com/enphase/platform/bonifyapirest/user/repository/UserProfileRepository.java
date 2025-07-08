package com.enphase.platform.bonifyapirest.user.repository;

import com.enphase.platform.bonifyapirest.user.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByEmail(String email);
}