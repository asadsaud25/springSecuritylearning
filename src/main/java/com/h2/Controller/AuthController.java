package com.h2.Controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.h2.Model.LoginRequest;
import com.h2.Model.LoginResponse;
import com.h2.Security.JwtIssuer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtIssuer jwtIssuer;
    
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        var token = jwtIssuer.issueToken(1L, request.getEmail() , List.of("USERS"));
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
