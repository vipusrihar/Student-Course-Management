package com.vipusa.management.controller;

import com.vipusa.management.config.jwt.JwtUtil;
import com.vipusa.management.request.AuthRequest;
import com.vipusa.management.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> authenticate(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // Generate JWT token
            String token = jwtUtil.generateToken(authentication.getName());

            ApiResponse<String> response = ApiResponse.<String>builder()
                    .response(token)
                    .message("Token generated successfully")
                    .success(true)
                    .build();

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            ApiResponse<String> response = ApiResponse.<String>builder()
                    .response(null)
                    .message("Invalid credentials")
                    .success(false)
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
