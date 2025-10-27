package com.examly.springapp.controller;

import com.examly.springapp.dto.*;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.security.CustomUserDetails;
import com.examly.springapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
    try {
        if (userRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        
        // store plain role string
        user.setRole(req.getRole() == null ? "USER" : req.getRole().toUpperCase());

        user.setCreatedDate(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setIsActive(true);

        User saved = userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "id", saved.getId(),
                "email", saved.getEmail(),
                "role", saved.getRole()
        ));
    } catch (Exception e) {
        e.printStackTrace(); // log to console
        return ResponseEntity.internalServerError().body(Map.of("error", "Registration failed", "details", e.getMessage()));
    }
}

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    try {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        CustomUserDetails ud = (CustomUserDetails) auth.getPrincipal();
        String role = ud.getAuthorities().stream().findFirst().map(Object::toString).orElse("ROLE_USER");

        String token = jwtUtil.generateToken(ud.getUsername(), ud.getId(), role);

        return ResponseEntity.ok(new AuthResponse(token, role)); // <-- include role here
    } catch (BadCredentialsException ex) {
        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }
}

    // ✅ Get current user info (requires valid JWT)
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        }

        Optional<User> userOpt = userRepository.findById(userDetails.getId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        User user = userOpt.get();
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole(),
                "active", user.isActive(),
                "createdDate", user.getCreatedDate(),
                "lastLogin", user.getLastLogin()
        ));
    }
}