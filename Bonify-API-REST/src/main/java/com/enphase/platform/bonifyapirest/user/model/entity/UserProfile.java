package com.enphase.platform.bonifyapirest.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
    private String profileImage;
    private String role;
}
