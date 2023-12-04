package com.ristify.ristifybackend.auth;

import com.ristify.ristifybackend.auth.message.AuthenticationRequest;
import com.ristify.ristifybackend.auth.message.AuthenticationResponse;
import com.ristify.ristifybackend.auth.message.RegisterRequest;
import com.ristify.ristifybackend.auth.message.RegisterResponse;
import com.ristify.ristifybackend.configurations.JwtService;
import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.models.user.User;
import com.ristify.ristifybackend.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(final RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .country(registerRequest.getCountry())
                .birthDate(registerRequest.getBirthDate())
                .build();

        return RegisterResponse.builder()
                .user(DTOMapper.mapUserToDTO(userRepository.save(user)))
                .token(jwtService.generateToken(user))
                .build();
    }

    public Optional<AuthenticationResponse> login(final AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        return userRepository.findByUsername(authenticationRequest.getUsername())
                .map(this::mapToAuthenticationResponse);
    }

    private AuthenticationResponse mapToAuthenticationResponse(final User user) {
        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .user(DTOMapper.mapUserToDTO(user))
                .build();
    }
}