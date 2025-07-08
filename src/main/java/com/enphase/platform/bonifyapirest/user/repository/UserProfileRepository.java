package com.enphase.platform.bonifyapirest.user.repository;

import com.enphase.platform.bonifyapirest.user.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {}
