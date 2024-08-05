package com.mmj.beprepared.dto.request;

import com.mmj.beprepared.model.Token;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Builder
@Data
public class AuthenticationRequestForUSer {

    private String email;
    private String password;

}
