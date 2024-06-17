package com.secureroute.SecureRoute.service;

import com.secureroute.SecureRoute.config.JwtService;
import com.secureroute.SecureRoute.models.*;
import com.secureroute.SecureRoute.repository.RoleRepository;
import com.secureroute.SecureRoute.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class
AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = new User();

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());

        Role role = roleRepository.findByName(request.getRole()).get();
        user.setRoles(Collections.singletonList(role));

        if(role.getName().equals("CHILDREN")) {
            user.setParent_id(request.getParent_id());
        }

        var createdUser = userService.createUser(user);
        var jwtToken = jwtService.generateToken(user);
        //need to send as a message map
        return AuthenticationResponse.builder().token(jwtToken).user(createdUser).build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        //need to send as a message map
        return new AuthenticationResponse(jwtToken, user);
    }
}
