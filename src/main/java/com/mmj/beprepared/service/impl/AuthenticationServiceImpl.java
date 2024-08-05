package com.mmj.beprepared.service.impl;

import com.mmj.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.mmj.beprepared.dto.request.AuthenticationRequestForUSer;
import com.mmj.beprepared.dto.response.TokenResponse;
import com.mmj.beprepared.model.Citizen;
import com.mmj.beprepared.model.Token;
import com.mmj.beprepared.model.User;
import com.mmj.beprepared.repository.CitizenRepository;
import com.mmj.beprepared.repository.TokenRepository;
import com.mmj.beprepared.repository.UserRepository;
import com.mmj.beprepared.security.JWTService;
import com.mmj.beprepared.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private  final JWTService jwtService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final CitizenRepository citizenRepository;
    private  final AuthenticationManager authenticationManager;


    @Override
    @Transactional
    public TokenResponse authenticate(AuthenticationRequestForUSer authenticationRequest) {
        authenticationManager.authenticate( //Logica para fazer a autenticacao
            new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            )
        );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var token = jwtService.generateToken(user);
        saveUserToken(user, token);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getPhone(),
                        authenticationRequest.getOtp()
                )
        );
        var citizen = citizenRepository.findByPhone(authenticationRequest.getPhone()).orElseThrow();
        var token = jwtService.generateToken(citizen);
        saveCitizensToken(citizen, token);
        citizen.setOtp(null);
        citizenRepository.save(citizen);
        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    private void saveUserToken(User user, String token){
        var jwtToken = Token.builder()
                .user(user)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }

    private void saveCitizensToken(Citizen citizen, String token){
        var jwtToken = Token.builder()
                .citizen(citizen)
                .token(token)
                .expired(false)
                .revoked(false)
                .createdAt(LocalDateTime.now())
                .build();
        tokenRepository.save(jwtToken);
    }


}
