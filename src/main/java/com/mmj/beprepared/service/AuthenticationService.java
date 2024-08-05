package com.mmj.beprepared.service;

import com.mmj.beprepared.dto.request.AuthenticationRequestForCitizen;
import com.mmj.beprepared.dto.request.AuthenticationRequestForUSer;
import com.mmj.beprepared.dto.response.TokenResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface AuthenticationService {

    TokenResponse authenticate(AuthenticationRequestForUSer authenticationRequest);

    TokenResponse authenticateCitizen(AuthenticationRequestForCitizen authenticationRequestForCitizen);

}
