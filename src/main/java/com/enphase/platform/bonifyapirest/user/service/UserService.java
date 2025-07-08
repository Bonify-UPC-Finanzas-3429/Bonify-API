package com.enphase.platform.bonifyapirest.user.service;

import com.enphase.platform.bonifyapirest.user.dto.UserProfileDTO;
import com.enphase.platform.bonifyapirest.user.dto.UserProfileUpdateDTO;

import java.util.List;

public interface UserService {
    List<UserProfileDTO> getAllUsers();
    UserProfileDTO getUserById(Long id);
    UserProfileDTO updateUser(Long id, UserProfileUpdateDTO dto);
    UserProfileDTO getUserByEmail(String email);
}
