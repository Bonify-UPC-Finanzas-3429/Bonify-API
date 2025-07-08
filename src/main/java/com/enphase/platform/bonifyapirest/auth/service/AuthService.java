package com.enphase.platform.bonifyapirest.auth.service;

import com.enphase.platform.bonifyapirest.auth.dto.*;

public interface AuthService {
    String signIn(SignInRequest request);
    void signUp(SignUpRequest request);
    void changePassword(ChangePasswordRequest request);
}
