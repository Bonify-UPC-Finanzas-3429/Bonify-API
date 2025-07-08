package com.enphase.platform.bonifyapirest.user.dto;

public record UserProfileUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String profileImage,
        String role
) {}
