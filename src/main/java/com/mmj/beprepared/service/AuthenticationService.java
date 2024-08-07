package com.mmj.beprepared.service;

import com.mmj.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.mmj.beprepared.dto.request.AuthenticationRequestForUSer;
import com.mmj.beprepared.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequestForUSer authenticationRequest);

    TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequest);

}
