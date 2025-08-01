package com.example.demo.service;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;


    public Boolean register(RegisterRequestDTO registerRequest) {
        if (userRepository.findByPhoneNumber(registerRequest.getPhoneNumber()).isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return true;
    }

    public AuthResponse login(AuthRequestDTO requestDTO) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.getPhoneNumber(),
                        requestDTO.getPassword()
                )
        );

        var userDetails = userDetailsService.loadUserByUsername(requestDTO.getPhoneNumber());
        String token = jwtUtil.generateToken(requestDTO.getPhoneNumber());
        return new AuthResponse(token);
    }



}
