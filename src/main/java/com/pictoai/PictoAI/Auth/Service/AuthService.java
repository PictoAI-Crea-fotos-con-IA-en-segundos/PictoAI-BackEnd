package com.pictoai.PictoAI.Auth.Service;

import com.pictoai.PictoAI.Auth.Dto.AuthResponse;
import com.pictoai.PictoAI.Auth.Dto.LoginRequest;
import com.pictoai.PictoAI.Auth.Dto.RegisterRequest;
import com.pictoai.PictoAI.Auth.Model.agreggates.User;
import com.pictoai.PictoAI.Auth.Model.enums.UserRol;
import com.pictoai.PictoAI.Auth.Repository.UserRepository;
import com.pictoai.PictoAI.Auth.Security.JWT.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    // espera un authResponse

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                            .token(jwt)
                            .build();
    }
    public AuthResponse register(RegisterRequest registerRequest){
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .role(UserRol.USER)
                .build();
        userRepository.save(user); // guarda el usuario
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
    public User getUserDetailsFromToken(String token) {
        String email = jwtService.extractEmail(token);
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
