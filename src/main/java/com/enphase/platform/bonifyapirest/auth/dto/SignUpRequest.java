package com.enphase.platform.bonifyapirest.auth.dto;

public record SignUpRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber,
        String role
) {}
