package com.pictoai.PictoAI.Auth.Service;

import com.pictoai.PictoAI.Auth.Dto.AuthResponse;
import com.pictoai.PictoAI.Auth.Dto.LoginRequest;
import com.pictoai.PictoAI.Auth.Dto.RegisterRequest;
import com.pictoai.PictoAI.Auth.Model.agreggates.User;
import com.pictoai.PictoAI.Auth.Model.enums.UserRol;
import com.pictoai.PictoAI.Auth.Repository.UserRepository;
import com.pictoai.PictoAI.Auth.Security.JWT.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    // espera un authResponse

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request){
        return null;
    }
    public AuthResponse register(RegisterRequest registerRequest){
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .role(UserRol.USER)
                .build();
        userRepository.save(user); // guarda el usuario
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
