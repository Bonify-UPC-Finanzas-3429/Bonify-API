package com.enphase.platform.bonifyapirest.auth.service;

import com.enphase.platform.bonifyapirest.auth.dto.*;
import com.enphase.platform.bonifyapirest.auth.model.AuthUser;
import com.enphase.platform.bonifyapirest.auth.repository.AuthUserRepository;
import com.enphase.platform.bonifyapirest.auth.security.JwtUtil;
import com.enphase.platform.bonifyapirest.shared.exception.ResourceNotFoundException;
import com.enphase.platform.bonifyapirest.user.model.entity.UserProfile;
import com.enphase.platform.bonifyapirest.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserProfileRepository userProfileRepository;
    @Override
    public String signIn(SignInRequest request) {
        var user = authUserRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    @Override
    @Transactional
    public void signUp(SignUpRequest request) {
        var user = AuthUser.builder()
                .username(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        var savedUser = authUserRepository.save(user);

        var userProfile = UserProfile.builder()
                .userId(savedUser.getId())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .profileImage(null)
                .role(request.role())
                .build();

        userProfileRepository.save(userProfile);
    }


    @Override
    public void changePassword(ChangePasswordRequest request) {
        var user = authUserRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        user.setPassword(passwordEncoder.encode(request.password()));
        authUserRepository.save(user);
    }
}
