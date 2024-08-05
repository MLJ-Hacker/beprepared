package com.mmj.beprepared.controller;


import com.mmj.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.mmj.beprepared.dto.request.AuthenticationRequestForUSer;
import com.mmj.beprepared.dto.response.TokenResponse;
import com.mmj.beprepared.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "1. Authentication Controller")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @Operation(summary = "Entre aqui para fazer a autenticação do ADMIN")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticationRequestForUSer authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Entre aqui para fazer a autenticação do CIDADÂO")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthenticationRequestForCitizen authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticateCitizen(authenticationRequest));

    }
}