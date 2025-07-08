package com.enphase.platform.bonifyapirest.user.dto;

public record UserProfileDTO(
        Long id,
        Long userId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String profileImage,
        String role
) {}
