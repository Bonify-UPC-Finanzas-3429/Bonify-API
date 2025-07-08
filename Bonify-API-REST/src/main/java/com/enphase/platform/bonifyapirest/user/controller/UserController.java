package com.enphase.platform.bonifyapirest.user.controller;

import com.enphase.platform.bonifyapirest.user.dto.UserProfileDTO;
import com.enphase.platform.bonifyapirest.user.dto.UserProfileUpdateDTO;
import com.enphase.platform.bonifyapirest.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserProfileUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }
}
