package com.mmj.beprepared.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenResponseDto {

    private long id;
    private String phone;
    private String deviceId;
    private String province;
    private String city;
    private boolean verified;
}
