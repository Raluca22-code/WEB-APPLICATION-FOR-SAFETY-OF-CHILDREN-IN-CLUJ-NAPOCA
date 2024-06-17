package com.secureroute.SecureRoute.controller;

import com.secureroute.SecureRoute.models.AuthenticationRequest;
import com.secureroute.SecureRoute.models.AuthenticationResponse;
import com.secureroute.SecureRoute.models.RegisterRequest;
import com.secureroute.SecureRoute.repository.RoleRepository;
import com.secureroute.SecureRoute.service.AuthenticationService;
import com.secureroute.SecureRoute.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register (
        @RequestBody RegisterRequest request) {
            if (userService.existsByEmail(request.getEmail())){
                return new ResponseEntity<>("A user with this email already exists!", HttpStatus.BAD_REQUEST);
            }
            if (!roleRepository.existsByName(request.getRole())) {
                return new ResponseEntity<>("Invalid Role!", HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(authService.register(request));
        }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));

    }
}
