package com.enphase.platform.bonifyapirest.user.service;

import com.enphase.platform.bonifyapirest.shared.exception.ResourceNotFoundException;
import com.enphase.platform.bonifyapirest.user.dto.*;
import com.enphase.platform.bonifyapirest.user.model.entity.UserProfile;
import com.enphase.platform.bonifyapirest.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProfileRepository repository;

    @Override
    public List<UserProfileDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public UserProfileDTO getUserById(Long id) {
        var profile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return toDTO(profile);
    }

    @Override
    public UserProfileDTO updateUser(Long id, UserProfileUpdateDTO dto) {
        var profile = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        profile.setFirstName(dto.firstName());
        profile.setLastName(dto.lastName());
        profile.setEmail(dto.email());
        profile.setPhoneNumber(dto.phoneNumber());
        profile.setProfileImage(dto.profileImage());
        profile.setRole(dto.role());

        return toDTO(repository.save(profile));
    }

    private UserProfileDTO toDTO(UserProfile user) {
        return new UserProfileDTO(
                user.getId(),
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getProfileImage(),
                user.getRole()
        );
    }
}
