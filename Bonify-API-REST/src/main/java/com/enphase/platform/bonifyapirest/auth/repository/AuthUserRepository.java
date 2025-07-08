package com.enphase.platform.bonifyapirest.auth.repository;

import com.enphase.platform.bonifyapirest.auth.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
}
